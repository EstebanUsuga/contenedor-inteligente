package com.reusoil.app.repository.resultado;

import com.reusoil.app.models.resultado.ResultadoAPI;
import com.reusoil.app.models.resultado.ResultadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultadoRepository extends JpaRepository<ResultadoEntity, Long> {


}
