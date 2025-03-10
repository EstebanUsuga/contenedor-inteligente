package com.reusoil.app.repository.perfil;

import com.reusoil.app.models.perfil.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {

    Optional<PerfilEntity> findById(Long id);

    Optional<PerfilEntity> findByDescPerfil(String descPerfil);

    // Método para encontrar perfiles por su estado (activo/inactivo)
    List<PerfilEntity> findByEstado(boolean estado);
}
