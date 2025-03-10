package com.reusoil.app.services.recoleccion;

import com.reusoil.app.models.recoleccion.RecoleccionEntity;

import java.util.List;
import java.util.Optional;

public interface RecoleccionService {
    void guardar(RecoleccionEntity recoleccion);

    void borradoLogico(Long id);

    List<RecoleccionEntity> obtenerTodos();

    List<RecoleccionEntity> obtenerPorEstado(boolean estado);

    Optional<RecoleccionEntity> obtenerPorId(Long id);
}
