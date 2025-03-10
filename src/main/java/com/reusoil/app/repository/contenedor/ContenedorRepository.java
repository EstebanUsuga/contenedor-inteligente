package com.reusoil.app.repository.contenedor;

import com.reusoil.app.models.contenedor.ContenedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenedorRepository extends JpaRepository<ContenedorEntity, Long> {
}

