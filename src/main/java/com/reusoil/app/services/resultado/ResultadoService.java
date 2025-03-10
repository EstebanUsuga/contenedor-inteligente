package com.reusoil.app.services.resultado;

import com.reusoil.app.models.resultado.ResultadoAPI;
import com.reusoil.app.models.resultado.ResultadoEntity;

import java.util.List;
import java.util.Optional;

public interface ResultadoService {

    void guardar(ResultadoAPI resultado);

    List<ResultadoEntity> obtenerTodos();

    Optional<ResultadoEntity> obtenerPorId(Long id);
}
