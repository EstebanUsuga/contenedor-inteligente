package com.reusoil.app.controller.contenedor;

import com.reusoil.app.models.contenedor.ContenedorEntity;
import com.reusoil.app.services.contenedor.ContenedorService;
import com.reusoil.app.services.empresa.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/contenedor")
public class ContenedorVistaController {

    private final ContenedorService contenedorService;
    private final EmpresaService empresaService;

    @GetMapping("/guardar-contenedor")
    public String mostrarFormularioContenedor(Model model) {
        ContenedorEntity contenedor = new ContenedorEntity();
        contenedor.setEstado(true);
        model.addAttribute("modoEdicion", false);
        model.addAttribute("contenedor", contenedor);
        model.addAttribute("empresas", empresaService.obtenerEmpresasPorEstado(true));
        return "vistas/contenedor/form_contenedor";
    }

    @GetMapping("/listado-contenedores")
    public String mostrarListadoContenedores(Model model) {

        //Recuerda implementar un método en CiudadService
        // para obtener ciudades por estado (el estado debe ser true para que se muestren)


        model.addAttribute("contenedores", contenedorService.obtenerContenedores());
        model.addAttribute("mensaje", "Listado de contenedores");
        return "vistas/contenedor/listado_contenedor";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Optional<ContenedorEntity> contenedor = contenedorService.obtenerContenedorPorId(id);
        if (contenedor.isPresent()) {
            model.addAttribute("contenedor", contenedor.get());
            model.addAttribute("empresas", empresaService.obtenerEmpresasPorEstado(true));
            model.addAttribute("modoEdicion", true);
            return "vistas/contenedor/form_contenedor";
        }
        return "redirect:/contenedor/listado-contenedores";
    }
}
