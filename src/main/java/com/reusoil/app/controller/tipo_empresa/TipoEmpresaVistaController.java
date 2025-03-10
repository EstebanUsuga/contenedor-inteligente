package com.reusoil.app.controller.tipo_empresa;

import com.reusoil.app.models.tipo_empresa.TipoEmpresaEntity;
import com.reusoil.app.services.tipo_empresa.TipoEmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/tipo-empresa")
public class TipoEmpresaVistaController {

    private final TipoEmpresaService tipoEmpresaService;

    @GetMapping("/listado")
    public String mostrarListadoTipoEmpresa(Model model) {
        model.addAttribute("tiposEmpresas", tipoEmpresaService.obtenerTipoEmpresasPorEstado(true));
        return "vistas/tipo-empresa/listado_tipo-empresa";
    }

    @GetMapping("/nuevo-tipo-empresa")
    public String mostrarFormularioTipoEmpresa(Model model) {
        TipoEmpresaEntity tipoEmpresa = new TipoEmpresaEntity();
        tipoEmpresa.setEstado(true);
        model.addAttribute("tipoEmpresa", tipoEmpresa);
        return "vistas/tipo-empresa/form_tipo-empresa";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Optional<TipoEmpresaEntity> tipoEmpresaOptional = tipoEmpresaService.obtenerTipoEmpresaPorId(id);
        if (tipoEmpresaOptional.isPresent()) {
            // Desenvuelve el Optional y pásalo directamente
            model.addAttribute("tipoEmpresa", tipoEmpresaOptional.get());
            return "vistas/tipo-empresa/form_tipo-empresa"; // Asegúrate de que el nombre del archivo coincida
        }
        return "redirect:/tipo-empresa/listado"; // Redirigir si no se encuentra el tipo de empresa
    }

//    @GetMapping("/consultar/{id}")
//    public String consultarTipoEmpresa(@PathVariable Long id, Model model) {
//        Optional<TipoEmpresaEntity> tipoEmpresa = tipoEmpresaService.obtenerTipoEmpresaPorId(id);
//        if (tipoEmpresa == null) {
//            return "redirect:/tipo-empresa/listado"; // Redirigir si no se encuentra el tipo de empresa
//        }
//        model.addAttribute("titulo", "Detalles de " + tipoEmpresa.getDescripcion()); // Suponiendo que hay un método getNombre
//        model.addAttribute("tipoEmpresa", tipoEmpresa);
//        return "vistas/tipo-empresa/consulta_tipo-empresa";
//    }
}
