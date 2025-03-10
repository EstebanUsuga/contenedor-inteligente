package com.reusoil.app.repository.recoleccion;
import com.reusoil.app.models.recoleccion.RecoleccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecoleccionRepository extends JpaRepository<RecoleccionEntity, Long> {
    List<RecoleccionEntity> findByEstado(boolean estado);
}
