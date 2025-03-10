package com.reusoil.app.services.tipo_sensor;

import com.reusoil.app.models.empresa.EmpresaEntity;
import com.reusoil.app.models.tipo_sensor.TipoSensorEntity;

import java.util.List;
import java.util.Optional;

public interface TipoSensorService {

    List<TipoSensorEntity> obtenerTiposSensor();

    Optional<TipoSensorEntity> obtenerTipoSensorPorId(Long id);
    void guardar(TipoSensorEntity tipoSensor);
    void eliminarTipoSensorPorId(Long id);

    Optional<TipoSensorEntity> obtenerTipoSensorPorDescripcion(String descripcion);

    void borradoLogico(Long id);

    List<TipoSensorEntity> obtenerTiposSensoresPorEstado(boolean estado);

}




