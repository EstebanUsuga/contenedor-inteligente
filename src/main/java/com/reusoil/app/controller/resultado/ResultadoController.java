package com.reusoil.app.controller.resultado;

import com.reusoil.app.models.resultado.ResultadoAPI;
import com.reusoil.app.models.resultado.ResultadoEntity;
import com.reusoil.app.services.resultado.ResultadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resultado")
public class ResultadoController {

    private final ResultadoService resultadoService;

    // Variable para verificar si se solicitó la obtención de resultados
    private boolean resultadoSolicitado = false;

    // Endpoint para recibir los datos de los sensores y guardar el resultado
    @PostMapping("/guardar")
    public ResponseEntity<String> guardarResultado(@RequestBody ResultadoAPI resultadoAPI) {
        resultadoService.guardar(resultadoAPI);
//        resultadoSolicitado = false; // Reinicia la solicitud de resultados
        return ResponseEntity.ok("Resultado recibido y almacenado.");
    }

    // Endpoint para solicitar los resultados desde la página web
    @PostMapping("/solicitarResultados")
    public ResponseEntity<String> solicitarResultados() {
        resultadoSolicitado = true;
        return ResponseEntity.ok("Inicia lectura de resultados.");
    }

    // Endpoint para verificar si se solicitó la obtención de resultados
    @GetMapping("/verificarSolicitud")
    public ResponseEntity<Boolean> verificarSolicitud() {
        return ResponseEntity.ok(resultadoSolicitado);
    }

    // Endpoint para obtener los resultados actuales de los sensores
    @GetMapping("/obtenerResultados")
    public List<ResultadoEntity> obtenerResultados() {
        List<ResultadoEntity> resultado = resultadoService.obtenerTodos();
        return ResponseEntity.ok(resultado).getBody();
    }

    // Página para iniciar la gestión de resultados
    @GetMapping("/resultadoGestion")
    public String iniciarResultado(Model model) {
        return "vistas/resultado/listado_resultado";
    }
}
