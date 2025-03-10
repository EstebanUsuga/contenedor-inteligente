package com.reusoil.app.controller.recoleccion;

import com.reusoil.app.models.recoleccion.RecoleccionEntity;
import com.reusoil.app.services.empresa.EmpresaService;
import com.reusoil.app.services.recoleccion.RecoleccionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/recoleccion")
@Controller
public class RecoleccionController {

    private final RecoleccionService recoleccionService;
    private final EmpresaService empresaService;

    @PostMapping("/guardar")
    public String crearOActualizarRecoleccion(@Valid @ModelAttribute("recoleccion") RecoleccionEntity recoleccion,
                                              BindingResult bindingResult,
                                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Por favor, corrija los errores en el formulario");
            model.addAttribute("empresas", empresaService.obtenerEmpresas());
            return "vistas/recoleccion/form_recoleccion";
        }

        recoleccionService.guardar(recoleccion);
        return "redirect:/recoleccion/listado-recolecciones";
    }

    @GetMapping("/formulario")
    public String mostrarFormularioRecoleccion(@RequestParam(required = false) Long id, Model model) {
        RecoleccionEntity recoleccion = (id != null) ? recoleccionService.obtenerPorId(id).orElse(null) : new RecoleccionEntity();
        model.addAttribute("recoleccion", recoleccion);
        model.addAttribute("modoEdicion", id != null);
        model.addAttribute("empresas", empresaService.obtenerEmpresas());
        return "vistas/recoleccion/form_recoleccion";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarRecoleccion(@PathVariable Long id) {
        recoleccionService.borradoLogico(id);
        return "redirect:/recoleccion/listado-recolecciones";
    }

}