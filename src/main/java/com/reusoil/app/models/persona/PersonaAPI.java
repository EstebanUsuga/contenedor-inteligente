package com.reusoil.app.models.persona;

import com.reusoil.app.models.empresa.EmpresaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonaAPI {

    private Long id;

    private String nombre;

    private String correo;

    private String telefono;

    private EmpresaEntity empresa;

    private boolean estado;
}
