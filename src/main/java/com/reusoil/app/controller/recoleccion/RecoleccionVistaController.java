package com.reusoil.app.controller.recoleccion;

import com.reusoil.app.models.recoleccion.RecoleccionEntity;
import com.reusoil.app.services.empresa.EmpresaService;
import com.reusoil.app.services.recoleccion.RecoleccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/recoleccion")
public class RecoleccionVistaController {

    private final RecoleccionService recoleccionService;
    private final EmpresaService empresaService;

    @GetMapping("/guardar-recoleccion")
    public String mostrarFormularioRecoleccion(Model model) {
        RecoleccionEntity recoleccion = new RecoleccionEntity();
        recoleccion.setEstado(true); // Suponiendo que el estado es verdadero por defecto
//        model.addAttribute("modoEdicion", false);
        model.addAttribute("recoleccion", recoleccion);
        model.addAttribute("empresas", empresaService.obtenerEmpresasPorEstado(true));
        return "vistas/recoleccion/form_recoleccion"; // Asegúrate de tener esta vista
    }

    @GetMapping("/listado-recolecciones")
    public String mostrarListadoRecolecciones(Model model) {
        model.addAttribute("recolecciones", recoleccionService.obtenerPorEstado(true)); // Obtener todas las recolecciones
        model.addAttribute("mensaje", "Listado de recolecciones");
        return "vistas/recoleccion/listado_recoleccion"; // Asegúrate de tener esta vista
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Optional<RecoleccionEntity> recoleccion = recoleccionService.obtenerPorId(id);
        if (recoleccion.isPresent()) {
            model.addAttribute("recoleccion", recoleccion.get());
            model.addAttribute("modoEdicion", true); // Indica que se está en modo de edición
            model.addAttribute("empresas", empresaService.obtenerEmpresasPorEstado(true));
            return "vistas/recoleccion/form_recoleccion"; // Reutiliza el mismo formulario
        }
        return "redirect:/recoleccion/listado-recolecciones"; // Redirige si no se encuentra la recolección
    }
}
