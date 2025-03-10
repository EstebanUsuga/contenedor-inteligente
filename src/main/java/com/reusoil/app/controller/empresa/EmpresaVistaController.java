package com.reusoil.app.controller.empresa;

import com.reusoil.app.models.empresa.EmpresaEntity;
import com.reusoil.app.services.ciudad.CiudadService;
import com.reusoil.app.services.empresa.EmpresaService;
import com.reusoil.app.services.tipo_empresa.TipoEmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/empresa")
public class EmpresaVistaController {

    private final CiudadService ciudadService;
    private final EmpresaService empresaService;
    private final TipoEmpresaService tipoEmpresaService;

    @GetMapping("/guardar-empresa")
    public String mostrarFormularioEmpresa(Model model) {
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setEstado(true);
        model.addAttribute("modoEdicion", false);
        model.addAttribute("empresa", empresa);
        model.addAttribute("ciudades", ciudadService.obtenerCiudades());
        model.addAttribute("tiposEmpresas", tipoEmpresaService.obtenerTiposEmpresa());
        return "vistas/empresa/form_empresa";
    }

    @GetMapping("/listado-empresas")
    public String mostrarListadoEmpresa(Model model) {
        model.addAttribute("empresas", empresaService.obtenerEmpresasPorEstado(true));
        model.addAttribute("mensaje", "Listado de empresas");
        return "vistas/empresa/listado_empresa";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Optional<EmpresaEntity> empresa = empresaService.obtenerEmpresaPorId(id);
        if (empresa.isPresent()) {
            model.addAttribute("empresa", empresa.get());
            model.addAttribute("ciudades", ciudadService.obtenerCiudadPorEstado(true));
            model.addAttribute("tiposEmpresas", tipoEmpresaService.obtenerTipoEmpresasPorEstado(true));
            model.addAttribute("modoEdicion", true);
            return "vistas/empresa/form_empresa";
        }
        return "redirect:/empresa/listado-empresas";
    }

    @GetMapping("/seleccionar-empresa")
    public String mostrarSeleccionEmpresa(Model model) {
        model.addAttribute("empresas", empresaService.obtenerEmpresasPorEstado(true));
        return "vistas/empresa/seleccion_empresa"; // Asegúrate de que el nombre de la vista coincida con el archivo HTML
    }


}