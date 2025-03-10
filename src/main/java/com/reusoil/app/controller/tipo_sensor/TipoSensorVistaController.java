package com.reusoil.app.controller.tipo_sensor;

import com.reusoil.app.models.tipo_sensor.TipoSensorAPI;
import com.reusoil.app.models.tipo_sensor.TipoSensorEntity;
import com.reusoil.app.services.tipo_sensor.TipoSensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tipo-sensor")
public class TipoSensorVistaController {

    private final TipoSensorService tipoSensorService;

    @GetMapping("/nuevo-tipo-sensor")
    public String mostrarFormularioTipoSensor(Model model) {
        TipoSensorAPI tipoSensor = new TipoSensorAPI();
        tipoSensor.setEstado(true);
        model.addAttribute("tipoSensor", tipoSensor);
        return "vistas/tipo-sensor/form_tipo-sensor";
    }

    @GetMapping("/listado-sensores")
    public String mostrarListadoTipoSensor(Model model) {
        model.addAttribute("tiposSensores", tipoSensorService.obtenerTiposSensoresPorEstado(true));
        return "vistas/tipo-sensor/listado_tipo-sensor";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Optional<TipoSensorEntity> tipoSensorOptional = tipoSensorService.obtenerTipoSensorPorId(id);
        if (tipoSensorOptional.isPresent()) {
            model.addAttribute("tipoSensor", tipoSensorOptional.get());
            return "vistas/tipo-sensor/form_tipo-sensor";
        }
        return "redirect:/tipo-sensor/listado-sensores";
    }

}
