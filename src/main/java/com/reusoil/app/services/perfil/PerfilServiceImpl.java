package com.reusoil.app.services.perfil;

import com.reusoil.app.models.perfil.PerfilEntity;
import com.reusoil.app.repository.perfil.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PerfilServiceImpl implements PerfilService {

    private final PerfilRepository perfilRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PerfilEntity> obtenerPerfiles() {
        return perfilRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PerfilEntity> obtenerPerfilPorId(Long id) {
        return perfilRepository.findById(id);
    }

    @Override
    @Transactional
    public void guardar(PerfilEntity perfil) {
        perfilRepository.save(perfil);
    }

    @Override
    @Transactional
    public void eliminarPerfilPorId(Long id) {
        perfilRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PerfilEntity> obtenerPerfilPorDescripcion(String descripcion) {
        return perfilRepository.findByDescPerfil(descripcion);
    }

    @Override
    @Transactional
    public void borradoLogico(Long id) {
        Optional<PerfilEntity> perfil = perfilRepository.findById(id);
        if (perfil.isPresent()) {
            PerfilEntity entidad = perfil.get();
            entidad.setEstado(false); // Marca como inactivo
            perfilRepository.save(entidad);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PerfilEntity> obtenerPerfilesPorEstado(boolean estado) {
        return perfilRepository.findByEstado(estado);
    }
}
