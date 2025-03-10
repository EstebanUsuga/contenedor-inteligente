package com.reusoil.app.models.tipo_sensor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tipo_sensor")
public class TipoSensorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo no puede estar vacío.")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÑñ ]*$", message = "Ingrese una descripción válida.")
    private String descripcion;

    private boolean estado;
}
