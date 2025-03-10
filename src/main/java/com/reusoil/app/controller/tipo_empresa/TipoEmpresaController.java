package com.reusoil.app.controller.tipo_empresa;

import com.reusoil.app.models.empresa.EmpresaEntity;
import com.reusoil.app.models.tipo_empresa.TipoEmpresaEntity;
import com.reusoil.app.services.tipo_empresa.TipoEmpresaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/tipo-empresa")
public class TipoEmpresaController {

    private final TipoEmpresaService tipoEmpresaService;

    // Guardar (crear o actualizar) un tipo de empresa
    @PostMapping("/guardar")
    public String crearOActualizarTipoEmpresa(@Valid @ModelAttribute("tipoEmpresa") TipoEmpresaEntity tipoEmpresa,
                                              BindingResult bindingResult, RedirectAttributes flash,
                                              Model model) {

        // Verificar errores de validación del formulario
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Por favor, corrija los errores en el formulario");
            return "vistas/tipo-empresa/form_tipo-empresa";
        }

        // Verificar si ya existe un tipo de empresa con la misma descripción
        Optional<TipoEmpresaEntity> tipoEmpresaExistente = tipoEmpresaService.obtenerTipoEmpresaPorDescripcion(tipoEmpresa.getDescripcion());

        if (tipoEmpresaExistente.isPresent() && !Objects.equals(tipoEmpresaExistente.get().getId(), tipoEmpresa.getId())) {
            // Si existe una descripción duplicada y no es el mismo registro
            bindingResult.rejectValue("descripcion", "error.tipoEmpresa", "Ya existe un tipo de empresa con esa descripción.");
            model.addAttribute("tipoEmpresa", tipoEmpresa); // Mantener los datos en el formulario
            return "vistas/tipo-empresa/form_tipo-empresa"; // Volver al formulario con mensaje de error
        }

        try {
            // Guardar o actualizar el tipo de empresa
            tipoEmpresaService.guardar(tipoEmpresa);
            flash.addFlashAttribute("success", "El tipo de empresa ha sido guardado correctamente");
            return "redirect:/tipo-empresa/listado"; // Redirigir a la lista de tipos de empresa
        } catch (Exception e) {
            // Manejo de errores generales
            model.addAttribute("error", "Ocurrió un error al guardar el tipo de empresa.");
            model.addAttribute("tipoEmpresa", tipoEmpresa); // Reenviar los datos en caso de error
            return "vistas/tipo-empresa/form_tipo-empresa";
        }
    }


    @PostMapping("/editar")
    public String guardarEdicion(@ModelAttribute("tipoEmpresa") TipoEmpresaEntity tipoEmpresa) {
        tipoEmpresaService.guardar(tipoEmpresa);
        return "redirect:/tipo-empresa/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTipoEmpresa(@PathVariable Long id) {
        tipoEmpresaService.borradoLogico(id);
        return "redirect:/tipo-empresa/listado"; // Redirigir después de eliminar
    }
}
