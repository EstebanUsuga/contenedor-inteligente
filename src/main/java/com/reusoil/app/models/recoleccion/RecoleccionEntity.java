package com.reusoil.app.models.recoleccion;

import com.reusoil.app.models.empresa.EmpresaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity(name = "recoleccion")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecoleccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha de recolección es obligatoria")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRecoleccion;

    @NotNull(message = "El volumen recogido es obligatorio")
    @Min(value = 0, message = "El volumen recogido debe ser mayor o igual a 0")
    @Column(nullable = false)
    private Float volumenRecogido;

    @ToString.Exclude
    @NotNull(message = "La empresa es obligatoria")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empresa", referencedColumnName = "id", nullable = false)
    private EmpresaEntity empresa;

    @Column(nullable = false)
    private boolean estado;
}
