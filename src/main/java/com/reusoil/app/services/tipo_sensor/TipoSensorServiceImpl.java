package com.reusoil.app.services.tipo_sensor;

import com.reusoil.app.models.tipo_sensor.TipoSensorEntity;
import com.reusoil.app.repository.tipo_sensor.TipoSensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TipoSensorServiceImpl implements TipoSensorService{

    private final TipoSensorRepository tipoSensorRepository;
    @Override
    @Transactional(readOnly = true)
    public List<TipoSensorEntity> obtenerTiposSensor() {
        return tipoSensorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoSensorEntity> obtenerTipoSensorPorId(Long id) {
        return tipoSensorRepository.findById(id);
    }

    @Override
    @Transactional
    public void guardar(TipoSensorEntity tipoSensor) {
        tipoSensorRepository.save(tipoSensor);
    }

    @Override
    @Transactional
    public void eliminarTipoSensorPorId(Long id) {
        tipoSensorRepository.deleteById(id);
    }

    @Override
    public Optional<TipoSensorEntity> obtenerTipoSensorPorDescripcion(String descripcion) {
        return tipoSensorRepository.findByDescripcion(descripcion);
    }

    public void borradoLogico(Long id) {
        Optional<TipoSensorEntity> tipoSensor = tipoSensorRepository.findById(id);
        if (tipoSensor.isPresent()) {
            TipoSensorEntity entidad = tipoSensor.get();
            entidad.setEstado(false); // Cambia el estado a false para indicar el borrado lógico
            tipoSensorRepository.save(entidad); // Guarda los cambios
        }
    }

    @Override
    public List<TipoSensorEntity> obtenerTiposSensoresPorEstado(boolean estado) {
        return tipoSensorRepository.findByEstado(estado);
    }

    public List<TipoSensorEntity> obtenerTiposSensorPorEstado(boolean estado) {
        return tipoSensorRepository.findByEstado(estado);
    }

}