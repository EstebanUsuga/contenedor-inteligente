package com.reusoil.app.models.resultado;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "resultado")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_medicion")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaMedicion = LocalDate.now();

    @Column(name = "resultado_temperatura", nullable = false)
    private float resultadoTemperatura;

    @Column(name = "nivel_llenado", nullable = false)
    private float nivelLlenado;

//    @ManyToOne
//    @JoinColumn(name = "id_configuracion", nullable = false)
//    private Configuracion configuracion;

    @Column(name = "estado", nullable = false)
    private boolean estado;

}
