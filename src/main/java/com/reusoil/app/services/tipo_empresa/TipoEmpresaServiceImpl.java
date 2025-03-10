package com.reusoil.app.services.tipo_empresa;

import com.reusoil.app.models.tipo_empresa.TipoEmpresaEntity;
import com.reusoil.app.repository.tipo_empresa.TipoEmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class TipoEmpresaServiceImpl implements TipoEmpresaService {

    final private TipoEmpresaRepository tipoEmpresaRepository;

    @Override
    public List<TipoEmpresaEntity> obtenerTiposEmpresa() {
        return tipoEmpresaRepository.findAll();
    }

    @Override
    public void guardar(TipoEmpresaEntity tipoEmpresa) {
        // Verificar si el ID no es nulo y si ya existe en la base de datos
        if (tipoEmpresa.getId() != null) {
            Optional<TipoEmpresaEntity> tipoEmpresaExistente = tipoEmpresaRepository.findById(tipoEmpresa.getId());
            if (tipoEmpresaExistente.isPresent()) {
                // Si el ID ya existe, actualizamos el registro existente
                TipoEmpresaEntity tipoEmpresaActualizada = tipoEmpresaExistente.get();

                // Actualizamos los campos relevantes del registro
//                tipoEmpresaActualizada.setNombre(tipoEmpresa.getNombre());
                tipoEmpresaActualizada.setDescripcion(tipoEmpresa.getDescripcion());
                // Aquí puedes actualizar cualquier otro campo necesario

                tipoEmpresaRepository.save(tipoEmpresaActualizada);
            } else {
                // Si el ID no existe en la base de datos, creamos un nuevo registro
                tipoEmpresa.setId(null); // Asegurar que el ID esté en null para crear un nuevo registro
                tipoEmpresaRepository.save(tipoEmpresa);
            }
        } else {
            // Si el ID es nulo, creamos un nuevo registro
            tipoEmpresaRepository.save(tipoEmpresa);
        }
    }

    public Optional<TipoEmpresaEntity> obtenerTipoEmpresaPorDescripcion(String descripcion) {
        return tipoEmpresaRepository.findByDescripcion(descripcion);
    }

    @Override
    public Optional<TipoEmpresaEntity> obtenerTipoEmpresaPorId(Long id) {
        return tipoEmpresaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<TipoEmpresaEntity> obtenerEmpresaPorId(Long id) {
        return tipoEmpresaRepository.findById(id);
    }


    @Override
    public void eliminarPorId(Long id) {
        tipoEmpresaRepository.deleteById(id);
    }

    @Override
    public void borradoLogico(Long id) {
        Optional<TipoEmpresaEntity> tipoEmpresa = tipoEmpresaRepository.findById(id);
        if (tipoEmpresa.isPresent()) {
            TipoEmpresaEntity entidad = tipoEmpresa.get();
            entidad.setEstado(false);
            tipoEmpresaRepository.save(entidad);
        }
    }

    @Override
    public List<TipoEmpresaEntity> obtenerTipoEmpresasPorEstado(boolean estado) {
        return tipoEmpresaRepository.findByEstado(estado);
    }
}
