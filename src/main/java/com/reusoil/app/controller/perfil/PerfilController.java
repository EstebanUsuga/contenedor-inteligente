package com.reusoil.app.controller.perfil;

import com.reusoil.app.models.ciudad.CiudadEntity;
import com.reusoil.app.models.perfil.PerfilEntity;
import com.reusoil.app.services.perfil.PerfilService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/perfil")
@Controller
public class PerfilController {

    private final PerfilService perfilService;

    @PostMapping("/guardar")
    public String crearOActualizarPerfil(@Valid @ModelAttribute("perfil") PerfilEntity perfil,
                                         BindingResult bindingResult,
                                         Model model) {
        // Verificar errores de validación
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Por favor, corrija los errores en el formulario");
            return "vistas/perfil/form_perfil"; // Volver al formulario con errores de validación
        }
        try {
            // Verificar si existe un tipo de sensor con la misma descripción
            Optional<PerfilEntity> perfilExistente = perfilService.obtenerPerfilPorDescripcion(perfil.getDescPerfil());

            // Si se encuentra una descripción duplicada y no es el mismo registro (evitar duplicados en actualización)
            if (perfilExistente.isPresent() && !perfilExistente.get().getId().equals(perfil.getId())) {
                bindingResult.rejectValue("descPerfil", "error.perfil", "Ya existe un perfil con esa descripción.");
                model.addAttribute("perfil", perfil); // Mantener datos en el formulario
                return "vistas/perfil/form_perfil"; // Volver al formulario con mensaje de error
            }

            perfilService.guardar(perfil);
            return "redirect:/perfil/listado-perfiles";

        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al guardar el perfil");
            model.addAttribute("perfil", perfil); // Reenviar los datos en caso de error
            return "vistas/perfil/form_perfil";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPerfil(@PathVariable Long id) {
        perfilService.borradoLogico(id);
        return "redirect:/perfil/listado-perfiles";
    }

    // Método para listar perfiles según su estado
    @GetMapping("/listado-perfiles/{estado}")
    public String listarPerfilesPorEstado(@PathVariable boolean estado, Model model) {
        List<PerfilEntity> perfiles = perfilService.obtenerPerfilesPorEstado(estado);
        model.addAttribute("perfiles", perfiles);
        return "vistas/perfil/listado_perfiles"; // Asegúrate de tener esta vista
    }
}
