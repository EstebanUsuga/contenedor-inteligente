package com.reusoil.app.services.ciudad;

import com.reusoil.app.models.ciudad.CiudadEntity;
import com.reusoil.app.repository.ciudad.CiudadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CiudadServiceImpl implements CiudadService {
    private final CiudadRepository ciudadRepository;

    @Override
    public List<CiudadEntity> obtenerCiudades() {
        // Modificar para obtener solo ciudades activas si es necesario
        return ciudadRepository.findByEstado(true);
    }

    @Override
    public void guardarCiudad(CiudadEntity ciudad) {
        if (ciudad.getId() != null) {
            Optional<CiudadEntity> ciudadExistente = ciudadRepository.findById(ciudad.getId());
            if (ciudadExistente.isPresent()) {
                CiudadEntity ciudadActualizada = ciudadExistente.get();
                ciudadActualizada.setNombre(ciudad.getNombre());
                // Actualiza cualquier otro campo necesario
                ciudadRepository.save(ciudadActualizada);
            } else {
                ciudad.setId(null); // Asegura que se cree un nuevo registro si el ID no existe
                ciudadRepository.save(ciudad);
            }
        } else {
            ciudadRepository.save(ciudad);
        }
    }




    @Override
    public Optional<CiudadEntity> obtenerCiudadPorId(Long id) {
        return ciudadRepository.findById(id);
    }

    @Override
    public Optional<CiudadEntity> obtenerCiudadPorNombre(String nombre) {
        return ciudadRepository.findByNombre(nombre);
    }

    @Override
    public void eliminarPorId(Long id) {
        ciudadRepository.deleteById(id); // Elimina físicamente el registro de la base de datos
    }

    @Override
    public void borradoLogico(Long id) {
        Optional<CiudadEntity> ciudadOptional = ciudadRepository.findById(id);
        if (ciudadOptional.isPresent()) {
            CiudadEntity ciudad = ciudadOptional.get();
            ciudad.setEstado(false); // Cambia el estado a falso para marcarla como inactiva
            ciudadRepository.save(ciudad);
        }
    }

    @Override
    public List<CiudadEntity> obtenerCiudadPorEstado(boolean estado) {
        return ciudadRepository.findByEstado(estado); // Asegúrate de tener este método en el repositorio
    }
}
