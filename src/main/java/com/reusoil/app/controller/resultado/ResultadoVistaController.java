package com.reusoil.app.controller.resultado;

import com.reusoil.app.models.resultado.ResultadoEntity;
import com.reusoil.app.repository.resultado.ResultadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/resultado")
@RequiredArgsConstructor
public class ResultadoVistaController {

    private final ResultadoRepository resultadoRepository;

    @GetMapping("/listar")
    public String mostrarResultados(Model model) {
        List<ResultadoEntity> resultados = resultadoRepository.findAll();

        // Imprimir los resultados en la consola
        resultados.forEach(resultado -> System.out.println(resultado));
        model.addAttribute("resultados", resultados);
        model.addAttribute("mensaje", "Listado de Resultados");
        return "vistas/resultado/listado_resultado"; // Nombre de la vista Thymeleaf
    }

    //para iniciar la pagina
    @GetMapping("/solicitarResultados")
    public String iniciarPeso(Model model) {
        return "vistas/resultado/obtener_resultado";
    }
}
