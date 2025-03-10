package com.reusoil.app.models.contenedor;

import com.reusoil.app.models.empresa.EmpresaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity(name = "contenedor")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContenedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "El campo no puede estar vacío.")
    private Float capacidad;

    @NotBlank(message = "El campo no puede estar vacío.")
    @Column(nullable = false)
    private String ubicacion;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInst;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_empresa", referencedColumnName = "id", nullable = false)
    private EmpresaEntity empresa;

    @Column(nullable = false)
    private boolean estado;

}
