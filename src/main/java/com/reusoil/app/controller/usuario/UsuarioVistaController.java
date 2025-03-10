package com.reusoil.app.controller.usuario;

import com.reusoil.app.models.persona.PersonaEntity;
import com.reusoil.app.models.registro.RegistroDTO;
import com.reusoil.app.models.usuario.UsuarioEntity;
import com.reusoil.app.services.perfil.PerfilService;
import com.reusoil.app.services.perfil.PerfilServiceImpl;
import com.reusoil.app.services.persona.PersonaService;
import com.reusoil.app.services.usuario.UsuarioService;
import com.reusoil.app.services.usuario.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/usuario")
public class UsuarioVistaController {

    private final UsuarioService usuarioService;
    private final PerfilService perfilService;
    private final PersonaService personaService;


    @GetMapping("/formulario-usuario")
    public String mostrarFormularioUsuario(Model model) {

        model.addAttribute("modoEdicion", false);
        model.addAttribute("perfiles", perfilService.obtenerPerfilesPorEstado(true));
        model.addAttribute("registroDTO", new RegistroDTO());
        return "vistas/usuario/personaUsuario";
    }

    @GetMapping("/listado-usuarios")
    public String mostrarListadoUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.obtenerUsuariosPorEstado(true));
        model.addAttribute("mensaje", "Listado de usuarios");
        return "vistas/usuario/listado_usuario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        PersonaEntity persona = personaService.obtenerPersonaPorId(id).get();
        UsuarioEntity usuario = usuarioService.obtenerUsuarioPorId(id).get();
        RegistroDTO registroDTO = RegistroDTO.from(persona, usuario);

        if (usuarioService.obtenerUsuarioPorId(id).isPresent()) {
            model.addAttribute("registroDTO", registroDTO);
            model.addAttribute("perfiles", perfilService.obtenerPerfilesPorEstado(true));
            model.addAttribute("modoEdicion", true);
            return "vistas/usuario/personaUsuario";
        }
        return "redirect:/usuario/listado-usuarios";
    }
}
