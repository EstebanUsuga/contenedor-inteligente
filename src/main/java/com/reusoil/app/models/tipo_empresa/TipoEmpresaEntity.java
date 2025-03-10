package com.reusoil.app.models.tipo_empresa;

import com.reusoil.app.models.empresa.EmpresaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Entity(name = "tipo_empresa")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TipoEmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo no puede estar vacío.")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÑñ ]*$", message = "Ingresar una descripción válida.")
    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private boolean estado;

    @ToString.Exclude
    @OneToMany(mappedBy = "tipoEmpresa", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<EmpresaEntity> empresas;

}
