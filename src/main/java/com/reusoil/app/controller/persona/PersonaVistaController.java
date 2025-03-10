package com.reusoil.app.controller.persona;

import com.reusoil.app.models.persona.PersonaEntity;
import com.reusoil.app.services.empresa.EmpresaService;
import com.reusoil.app.services.persona.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/persona")
public class PersonaVistaController {

    private final EmpresaService empresaService;
    private final PersonaService personaService;

    @GetMapping("/guardar-persona")
    public String mostrarFormularioPersona(Model model) {
        PersonaEntity persona = new PersonaEntity();
        persona.setEstado(true);
//        model.addAttribute("modoEdicion", false);
        model.addAttribute("persona", persona);
        model.addAttribute("empresas", empresaService.obtenerEmpresasPorEstado(true));
        return "vistas/persona/form_persona";
    }

    @GetMapping("/listado-personas")
    public String mostrarListadoPersona(Model model) {
        model.addAttribute("personas", personaService.obtenerPersonasPorEstado(true));
        model.addAttribute("mensaje", "Listado de personas");
        return "vistas/persona/listado_persona";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Optional<PersonaEntity> persona = personaService.obtenerPersonaPorId(id);
        if (persona.isPresent()) {
            model.addAttribute("persona", persona.get());
            model.addAttribute("empresas", empresaService.obtenerEmpresasPorEstado(true));
            model.addAttribute("modoEdicion", true);
            return "vistas/persona/form_persona";
        }
        return "redirect:/persona/listado-personas";
    }
}
