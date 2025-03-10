package com.reusoil.app.models.resultado;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ResultadoAPI {

    private float resultadoTemperatura;

    private float nivelLlenado;

    private LocalDate fechaMedicion = LocalDate.now();
}
