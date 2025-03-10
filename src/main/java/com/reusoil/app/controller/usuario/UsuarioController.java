package com.reusoil.app.controller.usuario;

import com.reusoil.app.models.perfil.PerfilEntity;
import com.reusoil.app.models.persona.PersonaEntity;
import com.reusoil.app.models.registro.RegistroDTO;
import com.reusoil.app.models.usuario.UsuarioAPI;
import com.reusoil.app.models.usuario.UsuarioEntity;
import com.reusoil.app.services.perfil.PerfilService;
import com.reusoil.app.services.persona.PersonaService;
import com.reusoil.app.services.usuario.UsuarioServiceImpl;
import com.reusoil.app.utils.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final EmailService emailService;
    private final PerfilService perfilService;
    private final PersonaService personaService;

    @PostMapping("/registrar")
    public String registrarUsuario(@ModelAttribute UsuarioAPI usuario) {
        usuarioService.guardarUsuario(
                UsuarioEntity.builder()
                        .usuario(usuario.getUsuario())
                        .clave(usuario.getClave())
                        .build()
        );
        return "redirect:/mostrar-login";
    }

    @PostMapping("/guardar")
    public String crearOActualizarUsuario(@Valid @ModelAttribute("registroDTO") RegistroDTO registroDTO,
                                          BindingResult bindingResult,
                                          Model model) {
        // Verificar errores de validación
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Por favor, corrija los errores en el formulario");
            return "vistas/usuario/personaUsuario";  // Volver al formulario con mensaje de error
        }

        try {
            // Validación para el correo duplicado
            Optional<PersonaEntity> identificacionExistente = personaService.obtenerPersonaPorId(Long.valueOf(registroDTO.getId()));
            if (identificacionExistente.isPresent()) {
                bindingResult.rejectValue("id", "error.id", "Esta identificación ya está registrada.");
                model.addAttribute("registroDTO", registroDTO);
                return "vistas/usuario/personaUsuario";  // Volver al formulario con mensaje de error
            }

            // Validación para el usuario duplicado
            Optional<UsuarioEntity> usuarioExistente = usuarioService.obtenerUsuarioPorUsuario(registroDTO.getUsuario());
            if (usuarioExistente.isPresent()) {
                bindingResult.rejectValue("usuario", "error.usuario", "Ya existe un usuario con ese nombre.");
                model.addAttribute("registroDTO", registroDTO);
                return "vistas/usuario/personaUsuario";  // Volver al formulario con mensaje de error
            }

            // Validación para el correo duplicado
            Optional<PersonaEntity> correoExistente = personaService.obtenerPersonaPorCorreo(registroDTO.getCorreo());
            if (correoExistente.isPresent()) {
                bindingResult.rejectValue("correo", "error.correo", "Este correo está en uso.");
                model.addAttribute("registroDTO", registroDTO);
                return "vistas/usuario/personaUsuario";  // Volver al formulario con mensaje de error
            }

            // Validación para el telefono duplicado
            Optional<PersonaEntity> telefonoExistente = personaService.obtenerPersonaPorTelefono(registroDTO.getTelefono());
            if (telefonoExistente.isPresent()) {
                bindingResult.rejectValue("telefono", "error.telefono", "Este número celular ya fue registrado.");
                model.addAttribute("registroDTO", registroDTO);
                return "vistas/usuario/personaUsuario";  // Volver al formulario con mensaje de error
            }

            // Obtener perfil de usuario
            PerfilEntity perfil = perfilService.obtenerPerfilPorDescripcion("Usuario").orElse(null);

            // Crear instancia de UsuarioEntity con los datos de RegistroDTO y perfil
            UsuarioEntity usuario = UsuarioEntity.from(registroDTO, perfil);

            // Guardar usuario en la base de datos
            usuarioService.guardarUsuario(usuario);

            // Crear y asociar la PersonaEntity con el UsuarioEntity
            PersonaEntity persona = PersonaEntity.from(registroDTO, usuario);

            // Guardar persona en la base de datos
            personaService.guardarPersona(persona);

            return "redirect:/usuario/listado-usuarios";

        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al guardar el usuario y la persona");
            model.addAttribute("registroDTO", registroDTO); // Reenviar los datos en caso de error
            return "vistas/usuario/personaUsuario";  // Volver al formulario con mensaje de error
        }
    }

    @GetMapping("/eliminar/{id}")
    public String borrarUsuario(@PathVariable Long id) {
        usuarioService.borradoLogico(id);
        return "redirect:/usuario/listado-usuarios";
    }

    @GetMapping("/usuarioRecuperarContrasena")
    public String recuperarUsuario(Model model) {
        UsuarioEntity usuario = new UsuarioEntity();
        model.addAttribute("usuario", usuario);
        model.addAttribute("mensaje", "");
        return "plantillas/recuperar-clave";
    }

    @PostMapping("/recuperarUsuario")
    public String recuperarContrasena(@RequestParam("correo") String correo, Model model, SessionStatus status) {
        //Usuario usuarioExistente = usuarioService.consultar(email);
        System.out.println("email: "+correo);

        List<UsuarioEntity> usuarios= usuarioService.obtenerUsuariosTodos();
        for (UsuarioEntity usuario : usuarios) {
            if (correo.equals( usuario.getUsuario())) {
                String contrasena = usuario.getClave(); // Obtener la contraseña

                String mensaje = "Su contraseña es: " + contrasena;
                emailService.enviarCorreo(correo, "Recuperación de contraseña", mensaje);

                status.setComplete();
                model.addAttribute("mensaje", "Se ha enviado su contraseña al correo electrónico.");
                System.out.println("Cumplio "+usuario.getUsuario());

                return "plantillas/recuperar-clave";
            }
            System.out.println("No cumplio "+usuario.getUsuario());
        }
        model.addAttribute("error", "No se encontró un usuario con ese email.");
        return "plantillas/recuperar-clave";

    }

    @GetMapping("/{id}")
    public UsuarioEntity obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id).orElse(null);
    }

}
