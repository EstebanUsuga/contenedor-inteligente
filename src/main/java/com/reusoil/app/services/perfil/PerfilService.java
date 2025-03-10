package com.reusoil.app.services.perfil;

import com.reusoil.app.models.perfil.PerfilEntity;

import java.util.List;
import java.util.Optional;

public interface PerfilService {
    List<PerfilEntity> obtenerPerfiles();
    Optional<PerfilEntity> obtenerPerfilPorId(Long id);
    void guardar(PerfilEntity perfil);
    void eliminarPerfilPorId(Long id);
    Optional<PerfilEntity> obtenerPerfilPorDescripcion(String descripcion);
    void borradoLogico(Long id);
    List<PerfilEntity> obtenerPerfilesPorEstado(boolean estado); // Agregado
}
