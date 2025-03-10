package com.reusoil.app.models.usuario;

import com.reusoil.app.models.perfil.PerfilEntity;
import com.reusoil.app.models.persona.PersonaEntity;
import com.reusoil.app.models.registro.RegistroDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity(name = "usuario")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo no puede estar vacío.")
    @Column(length = 60, nullable = false, unique = true)
    private String usuario;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "perfil_id")
    private PerfilEntity perfil;

    @NotBlank(message = "El campo no puede estar vacío.")
    @Column(nullable = false)
    private String clave;

    @OneToOne(mappedBy = "usuario")
    private PersonaEntity persona;

    private boolean estado;

    public static UsuarioEntity from(RegistroDTO registroDTO, PerfilEntity perfil) {
        return UsuarioEntity.builder()
                .id(Long.valueOf(registroDTO.getId()))
                .usuario(registroDTO.getUsuario())
                .perfil(perfil)
                .clave(registroDTO.getClave())
                .estado(true)
                .build();
    }

    public boolean areUsuarioAndClaveEmpty() {
        return usuario.isEmpty() || clave.isEmpty();
    }
}
