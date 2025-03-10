package com.reusoil.app.models.ciudad;

import com.reusoil.app.models.empresa.EmpresaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity(name = "ciudad")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CiudadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo no puede estar vacío.")
    @Size(max = 50, message = "El nombre no debe exceder 50 caracteres.")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÑñ ]*$", message = "El nombre de la ciudad no debe contener números ni caracteres especiales.")
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private boolean estado = true;

    @ToString.Exclude
    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.PERSIST)
    private List<EmpresaEntity> empresas;
}
