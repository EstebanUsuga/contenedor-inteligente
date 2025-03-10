package com.reusoil.app.services.persona;

import com.reusoil.app.models.empresa.EmpresaEntity;
import com.reusoil.app.models.persona.PersonaEntity;
import com.reusoil.app.repository.empresa.EmpresaRepository;
import com.reusoil.app.repository.persona.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonaServiceImpl implements PersonaService {

    final private PersonaRepository personaRepository;
    final private EmpresaRepository empresaRepository;


    @Override
    public List<PersonaEntity> obtenerPersonaTodos() {
        return personaRepository.findAll();
    }

    @Override
    public Optional<PersonaEntity> obtenerPersonaPorId(Long id) {
        return personaRepository.findById(id);
    }

    @Override
    public void eliminarPersonaPorId(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public void guardarPersona(PersonaEntity persona) {
        personaRepository.save(persona);
    }

    @Override
    public Optional<PersonaEntity> obtenerPersonaPorNombre(String nombre) {
        return personaRepository.findByNombre(nombre);
    }

    @Override
    public void borradoLogico(Long id) {
        Optional<PersonaEntity> persona = personaRepository.findById(id);
        if (persona.isPresent()) {
            PersonaEntity entidad = persona.get();
            entidad.setEstado(false);
            personaRepository.save(entidad);
        }
    }

    @Override
    public List<PersonaEntity> obtenerPersonasPorEstado(boolean estado) {
        return personaRepository.findByEstado(estado);
    }

    @Override
    public Optional<PersonaEntity> obtenerPersonaPorUsuarioId(Long id) {
        return personaRepository.findByUsuarioId(id);
    }

    @Override
    public Optional<PersonaEntity> obtenerPersonaPorCorreo(String correo) {
        return personaRepository.findByCorreo(correo);
    }

    @Override
    public Optional<PersonaEntity> obtenerPersonaPorTelefono(String telefono) {
        return personaRepository.findByTelefono(telefono);
    }

//    @Override
//    public void relacionarConEmpresa(Long empresaId, Long usuarioId) {
//        Optional<EmpresaEntity> empresa = empresaRepository.findById(empresaId);
//        Optional<PersonaEntity> persona = personaRepository.findById(usuarioId);
//        persona.setEmpresa(empresa);
//        personaRepository.save(persona);
//    }

}
