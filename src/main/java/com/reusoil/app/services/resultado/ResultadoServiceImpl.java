package com.reusoil.app.services.resultado;

import com.reusoil.app.models.resultado.ResultadoAPI;
import com.reusoil.app.models.resultado.ResultadoEntity;
import com.reusoil.app.repository.resultado.ResultadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ResultadoServiceImpl implements ResultadoService{

    private final ResultadoRepository resultadoRepository;
    @Override
    public void guardar(ResultadoAPI resultado) {
        try {
            ResultadoEntity resultadoGuardar = new ResultadoEntity();
            resultadoGuardar.setFechaMedicion(LocalDate.now());
            resultadoGuardar.setResultadoTemperatura(resultado.getResultadoTemperatura());
            resultadoGuardar.setNivelLlenado(resultado.getNivelLlenado());
            resultadoGuardar.setEstado(true);
            resultadoRepository.save(resultadoGuardar);
        } catch (Exception e) {
            // Maneja la excepción, por ejemplo, registrándola o lanzándola nuevamente
            System.out.println("Error al guardar el resultado: " + e.getMessage());
        }
    }

    @Override
    public List<ResultadoEntity> obtenerTodos() {
        return resultadoRepository.findAll();
    }

    @Override
    public Optional<ResultadoEntity> obtenerPorId(Long id) {
        return resultadoRepository.findById(id);
    }
}
