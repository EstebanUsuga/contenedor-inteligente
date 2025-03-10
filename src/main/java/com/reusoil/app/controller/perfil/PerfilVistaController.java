package com.reusoil.app.controller.perfil;

import com.reusoil.app.models.perfil.PerfilEntity;
import com.reusoil.app.services.perfil.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/perfil")
public class PerfilVistaController {

    private final PerfilService perfilService;

    @GetMapping("/guardar-perfil")
    public String mostrarFormularioPerfil(Model model) {
        PerfilEntity perfil = new PerfilEntity();
        perfil.setEstado(true);
        model.addAttribute("modoEdicion", false);
        model.addAttribute("perfil", perfil);
        return "vistas/perfil/form_perfil";
    }

    @GetMapping("/listado-perfiles")
    public String mostrarListadoPerfiles(Model model) {

        //Recuerda implementar un método en CiudadService
        // para obtener ciudades por estado (el estado debe ser true para que se muestren)

        model.addAttribute("perfiles", perfilService.obtenerPerfiles());
        model.addAttribute("mensaje", "Listado de perfiles");
        return "vistas/perfil/listado_perfil";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Optional<PerfilEntity> perfil = perfilService.obtenerPerfilPorId(id);
        if (perfil.isPresent()) {
            model.addAttribute("perfil", perfil.get());
            model.addAttribute("modoEdicion", true); // Indica que se está en modo de edición
            return "vistas/perfil/form_perfil"; // Reutiliza el mismo formulario
        }
        return "redirect:/perfil/listado-perfiles"; // Redirige si no se encuentra el perfil
    }
}
