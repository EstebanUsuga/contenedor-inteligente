package com.reusoil.app.services.tipo_empresa;

import com.reusoil.app.models.empresa.EmpresaEntity;
import com.reusoil.app.models.tipo_empresa.TipoEmpresaEntity;

import java.util.List;
import java.util.Optional;

public interface TipoEmpresaService {

    List<TipoEmpresaEntity> obtenerTiposEmpresa();

    void guardar(TipoEmpresaEntity tipoEmpresa);

    Optional<TipoEmpresaEntity> obtenerTipoEmpresaPorId(Long id);

    Optional<TipoEmpresaEntity> obtenerTipoEmpresaPorDescripcion(String descripcion);

    void eliminarPorId(Long id);

    void borradoLogico(Long id);

    List<TipoEmpresaEntity> obtenerTipoEmpresasPorEstado(boolean estado);

}