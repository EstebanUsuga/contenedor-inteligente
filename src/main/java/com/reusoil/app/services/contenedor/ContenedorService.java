package com.reusoil.app.services.contenedor;

import com.reusoil.app.models.contenedor.ContenedorEntity;
import java.util.List;
import java.util.Optional;

public interface ContenedorService {


    List<ContenedorEntity> obtenerContenedores();
    Optional<ContenedorEntity> obtenerContenedorPorId(Long id);
    void guardar(ContenedorEntity contenedor);
    void eliminarContenedorPorId(Long id);
}

