package com.reusoil.app.services.contenedor;

import com.reusoil.app.models.contenedor.ContenedorEntity;
import com.reusoil.app.repository.contenedor.ContenedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ContenedorServiceImpl implements ContenedorService {

    private final ContenedorRepository contenedorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ContenedorEntity> obtenerContenedores() {
        return contenedorRepository.findAll();
    }



    @Override
    @Transactional(readOnly = true)
    public Optional<ContenedorEntity> obtenerContenedorPorId(Long id) {
        return contenedorRepository.findById(id);
    }

    @Override
    @Transactional
    public void guardar(ContenedorEntity contenedor) {
        contenedorRepository.save(contenedor);
    }

    @Override
    @Transactional
    public void eliminarContenedorPorId(Long id) {
        contenedorRepository.deleteById(id);
    }
}
