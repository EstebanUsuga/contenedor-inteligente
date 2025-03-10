package com.reusoil.app.controller.ciudad;

import com.reusoil.app.models.ciudad.CiudadEntity;
import com.reusoil.app.services.ciudad.CiudadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ciudad")
public class CiudadController {

    private final CiudadService ciudadService;

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("titulo", "Nueva ciudad");
        model.addAttribute("ciudad", new CiudadEntity());
        return "vistas/ciudad/form_ciudad";
    }

    @PostMapping("/guardar")
    public String guardarCiudad(@Valid @ModelAttribute("ciudad") CiudadEntity ciudad,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("titulo", "Nueva ciudad");
            return "vistas/ciudad/form_ciudad";
        }

        try {
            // Verificar si existe un tipo de sensor con la misma descripción
            Optional<CiudadEntity> ciudadExistente = ciudadService.obtenerCiudadPorNombre(ciudad.getNombre());

            // Si se encuentra una descripción duplicada y no es el mismo registro (evitar duplicados en actualización)
            if (ciudadExistente.isPresent() && !ciudadExistente.get().getId().equals(ciudad.getId())) {
                bindingResult.rejectValue("nombre", "error.ciudad", "Ya existe una ciudad con ese nombre.");
                model.addAttribute("ciudad", ciudad); // Mantener datos en el formulario
                return "vistas/ciudad/form_ciudad"; // Volver al formulario con mensaje de error
            }

            ciudadService.guardarCiudad(ciudad);
            return "redirect:/ciudad/mostrar-ciudades";
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al guardar la ciudad");
            model.addAttribute("ciudad", ciudad); // Reenviar los datos en caso de error
            return "vistas/ciudad/form_ciudad";
        }


    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCiudad(@PathVariable Long id) {
        ciudadService.borradoLogico(id);
        return "redirect:/ciudad/mostrar-ciudades";
    }
}
