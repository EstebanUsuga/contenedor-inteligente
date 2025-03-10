package com.reusoil.app.services.usuario;

import com.reusoil.app.models.tipo_sensor.TipoSensorEntity;
import com.reusoil.app.models.usuario.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<UsuarioEntity> obtenerUsuariosTodos();

    Optional<UsuarioEntity> obtenerUsuarioPorId(Long id);
    void eliminarUsuarioPorId(Long id);
    UsuarioEntity guardarUsuario(UsuarioEntity usuarioEntity);
    Optional<UsuarioEntity> obtenerUsuarioPorUsuario(String usuario);
    void borradoLogico(Long id);
    List<UsuarioEntity> obtenerUsuariosPorEstado(boolean estado);
    public boolean existeUsuarioPorNombre(String nombreUsuario);

}
