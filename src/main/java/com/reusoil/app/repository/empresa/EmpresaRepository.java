package com.reusoil.app.repository.empresa;

import com.reusoil.app.models.empresa.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {

    Optional<EmpresaEntity> findById(Long id);

    Optional<EmpresaEntity> findByNombre(String nombre);

    List<EmpresaEntity> findByEstado(boolean estado);

    Optional<EmpresaEntity> findByCorreo(String correo);

}
