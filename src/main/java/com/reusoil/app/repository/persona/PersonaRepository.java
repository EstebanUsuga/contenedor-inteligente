package com.reusoil.app.repository.persona;

import com.reusoil.app.models.persona.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {

    Optional<PersonaEntity> findByNombre(String nombre);
    List<PersonaEntity> findByEstado(boolean estado);
    Optional<PersonaEntity> findByUsuarioId(Long id);

    Optional<PersonaEntity> findByCorreo(String correo);

    Optional<PersonaEntity> findByTelefono(String telefono);

//    void relacionarConEmpresa(Long empresaId, Long usuarioId);
}
