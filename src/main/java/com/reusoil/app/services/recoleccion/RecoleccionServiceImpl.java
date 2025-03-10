package com.reusoil.app.services.recoleccion;

import com.reusoil.app.models.recoleccion.RecoleccionEntity;
import com.reusoil.app.repository.recoleccion.RecoleccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class RecoleccionServiceImpl implements RecoleccionService {

    // Aquí inyecta el repositorio correspondiente
    private final RecoleccionRepository recoleccionRepository;

    @Override
    public void guardar(RecoleccionEntity recoleccion) {
        recoleccionRepository.save(recoleccion);
    }

    @Override
    public void borradoLogico(Long id) {
        // Implementa tu lógica de borrado lógico aquí
        RecoleccionEntity recoleccion = recoleccionRepository.findById(id).orElseThrow(); // Manejo de excepciones según sea necesario
        recoleccion.setEstado(false); // Suponiendo que esto es un borrado lógico
        recoleccionRepository.save(recoleccion);
    }

    @Override
    public List<RecoleccionEntity> obtenerTodos() {
        return recoleccionRepository.findAll();
    }

    @Override
    public List<RecoleccionEntity> obtenerPorEstado(boolean estado) {
        return recoleccionRepository.findByEstado(estado); // Asegúrate de tener este método en tu repositorio
    }

    @Override
    public Optional<RecoleccionEntity> obtenerPorId(Long id) {
        return recoleccionRepository.findById(id);
    }
}
