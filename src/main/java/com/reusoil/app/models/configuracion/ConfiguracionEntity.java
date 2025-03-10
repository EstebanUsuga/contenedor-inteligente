//package com.reusoil.app.models.configuracion;
//
//import com.reusoil.app.models.resultado.ResultadoEntity;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Entity(name = "configuracion")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class ConfiguracionEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "nombre_configuracion", nullable = false)
//    private String nombreConfiguracion;
//
//    @OneToMany(mappedBy = "configuracion", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ResultadoEntity> resultados;
//
//}
