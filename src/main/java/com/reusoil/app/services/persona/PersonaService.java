package com.reusoil.app.services.persona;

import com.reusoil.app.models.persona.PersonaEntity;

import java.util.List;
import java.util.Optional;

public interface PersonaService {

    List<PersonaEntity> obtenerPersonaTodos();
    Optional<PersonaEntity> obtenerPersonaPorId(Long id);
    void eliminarPersonaPorId(Long id);
    void guardarPersona(PersonaEntity persona);
    Optional<PersonaEntity> obtenerPersonaPorNombre(String nombre);

    void borradoLogico(Long id);
    List<PersonaEntity> obtenerPersonasPorEstado(boolean estado);

    Optional<PersonaEntity> obtenerPersonaPorUsuarioId(Long id);

    Optional<PersonaEntity> obtenerPersonaPorCorreo(String correo);

    Optional<PersonaEntity> obtenerPersonaPorTelefono(String telefono);

//    void relacionarConEmpresa(Long empresaId, Long usuarioId);
}
