package com.reusoil.app.models.persona;

import com.reusoil.app.models.empresa.EmpresaEntity;
import com.reusoil.app.models.registro.RegistroDTO;
import com.reusoil.app.models.usuario.UsuarioEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity(name = "persona")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco.")
    @Column(length = 60, nullable = false, unique = true)
    private String nombre;

    @NotBlank(message = "El nombre no puede estar en blanco.")
    private String correo;

    @NotBlank(message = "El nombre no puede estar en blanco.")
    private String telefono;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "empresa_id")
    private EmpresaEntity empresa;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UsuarioEntity usuario;

    private boolean estado;

    public static PersonaEntity from(RegistroDTO registroDTO, UsuarioEntity usuario){
        return PersonaEntity.builder()
                .id((registroDTO.getId()))
                .nombre(registroDTO.getNombre())
                .correo(registroDTO.getCorreo())
                .telefono(registroDTO.getTelefono())
                .usuario(usuario)
                .estado(true)
                .build();
    }

//    public static void relacionarConEmpresa(Long empresaId, Long usuarioId){
//        EmpresaEntity empresa = empresaService.obtenerEmpresaPorId(empresaId);
//        return PersonaEntity.builder().empresa(empresa)
//    }

}
