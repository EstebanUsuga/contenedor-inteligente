package com.reusoil.app.models.tipo_sensor;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TipoSensorAPI {

    private Long id;

    private String descripcion;

    private boolean estado;
}

