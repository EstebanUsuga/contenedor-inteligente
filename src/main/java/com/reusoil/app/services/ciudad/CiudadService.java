package com.reusoil.app.services.ciudad;

import com.reusoil.app.models.ciudad.CiudadEntity;
import com.reusoil.app.models.tipo_empresa.TipoEmpresaEntity;

import java.util.List;
import java.util.Optional;

public interface  CiudadService {

    List<CiudadEntity> obtenerCiudades();

    void guardarCiudad(CiudadEntity ciudad);

    Optional<CiudadEntity> obtenerCiudadPorId(Long id);

    Optional<CiudadEntity> obtenerCiudadPorNombre(String nombre);

    void eliminarPorId(Long id);

    void borradoLogico(Long id);

    List<CiudadEntity> obtenerCiudadPorEstado(boolean estado);


}
