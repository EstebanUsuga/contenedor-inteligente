package com.reusoil.app.controller.tipo_sensor;

import com.reusoil.app.models.tipo_sensor.TipoSensorEntity;
import com.reusoil.app.services.tipo_sensor.TipoSensorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tipo-sensor")
public class
TipoSensorController {

    private final TipoSensorService tipoSensorService;

    // Guarda un nuevo sensor o actualiza uno existente
    @PostMapping("/guardar")
    public String crearOActualizarTipoSensor(@Valid @ModelAttribute("tipoSensor") TipoSensorEntity tipoSensor,
                                             BindingResult bindingResult,
                                             RedirectAttributes flash,
                                             Model model) {
        // Verificar errores de validación
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Por favor, corrija los errores en el formulario");
            return "vistas/tipo-sensor/form_tipo-sensor"; // Volver al formulario con errores de validación
        }

        try {
            // Verificar si existe un tipo de sensor con la misma descripción
            Optional<TipoSensorEntity> tipoSensorExistente = tipoSensorService.obtenerTipoSensorPorDescripcion(tipoSensor.getDescripcion());

            // Si se encuentra una descripción duplicada y no es el mismo registro (evitar duplicados en actualización)
            if (tipoSensorExistente.isPresent() && !tipoSensorExistente.get().getId().equals(tipoSensor.getId())) {
                bindingResult.rejectValue("descripcion", "error.tipoSensor", "Ya existe un tipo de sensor con esa descripción.");
                model.addAttribute("tipoSensor", tipoSensor); // Mantener datos en el formulario
                return "vistas/tipo-sensor/form_tipo-sensor"; // Volver al formulario con mensaje de error
            }

            // Guardar o actualizar el tipo de sensor
            tipoSensorService.guardar(tipoSensor);
            flash.addFlashAttribute("success", "El tipo de sensor ha sido guardado correctamente");
            return "redirect:/tipo-sensor/listado-sensores"; // Redirigir a la lista de tipos de sensor
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al guardar el tipo de sensor.");
            model.addAttribute("tipoSensor", tipoSensor); // Reenviar los datos en caso de error
            return "vistas/tipo-sensor/form_tipo-sensor";
        }
    }

    // Guarda los cambios de la edición de un sensor
    @PostMapping("/editar")
    public String guardarEdicion(@ModelAttribute("tipoSensor") TipoSensorEntity tipoSensor) {
        tipoSensorService.guardar(tipoSensor);
        return "redirect:/tipo-sensor/listado-sensores"; // Redirigir a la lista correcta después de editar
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTipoSensor(@PathVariable Long id) {
        tipoSensorService.borradoLogico(id);
        return "redirect:/tipo-sensor/listado-sensores";
    }
}
