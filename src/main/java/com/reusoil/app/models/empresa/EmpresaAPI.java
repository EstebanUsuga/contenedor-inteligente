package com.reusoil.app.models.empresa;

import com.reusoil.app.models.ciudad.CiudadEntity;
import com.reusoil.app.models.persona.PersonaEntity;
import com.reusoil.app.models.tipo_empresa.TipoEmpresaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaAPI {

    private String nombre;

    private String direccion;

    private String telefono;

    private String email;

    private Date fechaRegistro;

    private CiudadEntity ciudad;

    private TipoEmpresaEntity tipoEmpresa;

    private List<PersonaEntity> personas;

    private boolean estado;
}
