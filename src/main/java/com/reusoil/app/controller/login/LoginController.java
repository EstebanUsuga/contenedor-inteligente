package com.reusoil.app.controller.login;

import com.reusoil.app.models.perfil.PerfilEntity;
import com.reusoil.app.models.registro.RegistroDTO;
import com.reusoil.app.models.persona.PersonaEntity;
import com.reusoil.app.models.usuario.UsuarioEntity;
import com.reusoil.app.services.perfil.PerfilService;
import com.reusoil.app.services.persona.PersonaService;
import com.reusoil.app.services.usuario.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioService usuarioService;
    private final PersonaService personaService;
    private final PerfilService perfilService;

    @PostMapping("/registrar")
    public String registrarUsuario(@Valid @ModelAttribute("registroDTO") RegistroDTO registroDTO,
                                   BindingResult bindingResult, Model model) {

        model.addAttribute("usuarioLogin", new UsuarioEntity());

        // Verificar si hay errores de validación en los campos básicos
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Por favor, corrija los errores en el formulario");
            return "vistas/inicio/login";
        }

        // Validación para el correo duplicado
        Optional<PersonaEntity> identificacionExistente = personaService.obtenerPersonaPorId(Long.valueOf(registroDTO.getId()));
        if (identificacionExistente.isPresent()) {
            bindingResult.rejectValue("id", "error.id", "Esta identificación ya está registrada.");
            model.addAttribute("registroDTO", registroDTO);
            return "vistas/inicio/login";
        }

        // Validación para el usuario duplicado
        Optional<UsuarioEntity> usuarioExistente = usuarioService.obtenerUsuarioPorUsuario(registroDTO.getUsuario());
        if (usuarioExistente.isPresent()) {
            bindingResult.rejectValue("usuario", "error.usuario", "Ya existe un usuario con ese nombre.");
            model.addAttribute("registroDTO", registroDTO);
            return "vistas/inicio/login";
        }

        // Validación para el correo duplicado
        Optional<PersonaEntity> correoExistente = personaService.obtenerPersonaPorCorreo(registroDTO.getCorreo());
        if (correoExistente.isPresent()) {
            bindingResult.rejectValue("correo", "error.correo", "Este correo está en uso.");
            model.addAttribute("registroDTO", registroDTO);
            return "vistas/inicio/login";
        }

        // Validación para el telefono duplicado
        Optional<PersonaEntity> telefonoExistente = personaService.obtenerPersonaPorTelefono(registroDTO.getTelefono());
        if (telefonoExistente.isPresent()) {
            bindingResult.rejectValue("telefono", "error.telefono", "Este número celular ya fue registrado.");
            model.addAttribute("registroDTO", registroDTO);
            return "vistas/inicio/login";
        }

        PerfilEntity perfil = perfilService.obtenerPerfilPorDescripcion("Usuario").orElse(null);

        UsuarioEntity usuario = UsuarioEntity.from(registroDTO, perfil);

        usuarioService.guardarUsuario(usuario);

        PersonaEntity persona = PersonaEntity.from(registroDTO, usuario);

        personaService.guardarPersona(persona);

// Guardar usuario y persona en la base de datos

        return "redirect:/mostrar-login";
    }


    @PostMapping("/login")
    public String iniciarSesion(UsuarioEntity usuario, Model model, HttpServletRequest request) {

        model.addAttribute("registroDTO", new RegistroDTO());

        var usuarioActual = usuarioService.obtenerUsuarioPorUsuario(usuario.getUsuario());

        // Verifica si los campos están vacíos
        if (usuario.areUsuarioAndClaveEmpty()) {
            model.addAttribute("usuarioLogin", new UsuarioEntity());
            model.addAttribute("errorLogin", "Ingrese sus credenciales, por favor.");
            return "vistas/inicio/login";
        }
        if (usuarioActual.isEmpty()) {
            model.addAttribute("usuarioLogin", new UsuarioEntity());
            model.addAttribute("errorLogin", "El usuario ingresado no existe");
            return "vistas/inicio/login";
        }
        UsuarioEntity usuarioEntity = usuarioActual.get();
        if (!usuario.getClave().equals(usuarioEntity.getClave())) {
            model.addAttribute("usuarioLogin", new UsuarioEntity());
            model.addAttribute("errorLogin", "Usuario o contraseña incorrectos.");
            return "vistas/inicio/login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("usuarioId", usuarioEntity.getId());

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate(); // Cierra sesión
        return "redirect:/login";
    }

//
//    @PostMapping("/home")
//    public String homePage() {
//        return "vistas/homepage";
//    }

}
