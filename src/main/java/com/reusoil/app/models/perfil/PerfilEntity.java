package com.reusoil.app.models.perfil;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "perfil")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco.")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÑñ ]*$", message = "Ingrese una descripción válida.")
    @Column(name = "descripcion_perfil", nullable = false)
    private String descPerfil;

    @Column(name = "estado")
    private boolean estado;

    public boolean getEstado(){
        return estado;
    }
}
