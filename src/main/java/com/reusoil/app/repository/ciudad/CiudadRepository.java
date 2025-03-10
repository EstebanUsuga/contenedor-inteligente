package com.reusoil.app.repository.ciudad;

import com.reusoil.app.models.ciudad.CiudadEntity;
import com.reusoil.app.models.empresa.EmpresaEntity;
import com.reusoil.app.models.tipo_empresa.TipoEmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CiudadRepository extends JpaRepository<CiudadEntity, Long> {

    Optional<CiudadEntity> findById(Long id);

    Optional<CiudadEntity> findByNombre(String nombre);

    List<CiudadEntity> findByEstado(boolean estado);

}
