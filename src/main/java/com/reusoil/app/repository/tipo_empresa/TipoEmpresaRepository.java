package com.reusoil.app.repository.tipo_empresa;

import com.reusoil.app.models.tipo_empresa.TipoEmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoEmpresaRepository extends JpaRepository<TipoEmpresaEntity, Long> {

    Optional<TipoEmpresaEntity> findById(Long id);

    Optional<TipoEmpresaEntity> findByDescripcion(String descripcion);

    List<TipoEmpresaEntity> findByEstado(boolean estado);

}
