package com.reusoil.app.controller.persona;

import com.reusoil.app.models.persona.PersonaEntity;
import com.reusoil.app.services.empresa.EmpresaService;
import com.reusoil.app.services.persona.PersonaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/persona")
@Controller
public class PersonaController {

    private final EmpresaService empresaService;
    private final PersonaService personaService;

    @PostMapping("/guardar")
    public String crearOActualizarPersona(@Valid @ModelAttribute("persona") PersonaEntity persona,
                                          BindingResult bindingResult,
                                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Por favor, corrija los errores en el formulario");
            model.addAttribute("modoEdicion", true);
            model.addAttribute("persona", persona);
            model.addAttribute("empresas", empresaService.obtenerEmpresasPorEstado(true));
            return "vistas/persona/form_persona";
        }

        try {
            Optional<PersonaEntity> personaExistente = personaService.obtenerPersonaPorId(persona.getId());

            if (personaExistente.isPresent()) {
                PersonaEntity personaOriginal = personaExistente.get();

                if (personaOriginal.getNombre().equals(persona.getNombre())) {
                    bindingResult.rejectValue("nombre", "error.persona", "Ya existe una persona con el mismo nombre");
                    model.addAttribute("modoEdicion", true);
                    model.addAttribute("persona", persona);
                    model.addAttribute("empresas", empresaService.obtenerEmpresasPorEstado(true));
                    return "vistas/persona/form_persona";
                }

                if (personaOriginal.getCorreo().equals(persona.getCorreo())) {
                    bindingResult.rejectValue("correo", "error.persona", "Ya existe una persona con el mismo correo");
                    model.addAttribute("modoEdicion", true);
                    model.addAttribute("persona", persona);
                    model.addAttribute("empresas", empresaService.obtenerEmpresasPorEstado(true));
                    return "vistas/persona/form_persona";
                }
            }

            personaService.guardarPersona(persona);
            return "redirect:/persona/listado-personas";
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al guardar la persona. Intente nuevamente.");
            model.addAttribute("modoEdicion", true);
            model.addAttribute("persona", persona);
            model.addAttribute("empresas", empresaService.obtenerEmpresasPorEstado(true));
            return "vistas/persona/form_persona";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Long id) {
        personaService.borradoLogico(id);
        return "redirect:/persona/listado-personas";
    }
}
