package com.reusoil.app.models.tipo_empresa;

import com.reusoil.app.models.empresa.EmpresaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TipoEmpresaAPI {

    private Long id;

    private String descripcion;

    private boolean estado;

//    private List<EmpresaEntity> empresas;
}
