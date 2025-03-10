package com.reusoil.app.repository.tipo_sensor;

import com.reusoil.app.models.tipo_sensor.TipoSensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoSensorRepository extends JpaRepository<TipoSensorEntity, Long> {

    Optional<TipoSensorEntity> findByDescripcion(String descripcion);

    List<TipoSensorEntity> findByEstado(boolean estado);
}
