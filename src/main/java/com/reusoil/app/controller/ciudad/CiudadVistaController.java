package com.reusoil.app.controller.ciudad;

import com.reusoil.app.models.ciudad.CiudadEntity;
import com.reusoil.app.services.ciudad.CiudadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ciudad")
public class CiudadVistaController {

    private final CiudadService ciudadService;

    @GetMapping("/nueva-ciudad")
    public String mostrarFormularioCiudad(Model model) {
        CiudadEntity ciudad = new CiudadEntity();
        ciudad.setEstado(true); // Inicializa el estado como verdadero
        model.addAttribute("ciudad", ciudad);
        model.addAttribute("titulo", "Nueva ciudad");
        return "vistas/ciudad/form_ciudad"; // Muestra el formulario para crear una nueva ciudad
    }

    @GetMapping("/mostrar-ciudades")
    public String mostrarCiudades(Model model) {
        model.addAttribute("ciudades", ciudadService.obtenerCiudades());
        return "vistas/ciudad/listado_ciudad"; // Muestra la lista de ciudades
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Optional<CiudadEntity> ciudadOptional = ciudadService.obtenerCiudadPorId(id);
        if (ciudadOptional.isPresent()) {
            model.addAttribute("ciudad", ciudadOptional.get());
            model.addAttribute("titulo", "Editar ciudad");
            return "vistas/ciudad/form_ciudad"; // Muestra el formulario para editar la ciudad
        }
        return "redirect:/ciudad/mostrar-ciudades"; // Redirige si no se encuentra la ciudad
    }
}
