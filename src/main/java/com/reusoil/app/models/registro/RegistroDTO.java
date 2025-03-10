package com.reusoil.app.models.registro;

import com.reusoil.app.models.persona.PersonaEntity;
import com.reusoil.app.models.usuario.UsuarioEntity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDTO {


        @NotNull(message = "El documento de identidad es obligatorio")
        private Long id;

        @NotBlank(message = "El nombre completo es obligatorio")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÑñ ]*$", message = "El nombre solo debe contener letras y espacios")
        private String nombre;

        @NotBlank(message = "El usuario es obligatorio")
        @Size(min = 4, max = 20, message = "El usuario debe tener entre 4 y 20 caracteres")
        private String usuario;

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo debe tener un formato válido")
        private String correo;

        @NotBlank(message = "El teléfono es obligatorio")
        private String telefono;

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        private String clave;

        public static RegistroDTO from(PersonaEntity persona, UsuarioEntity usuario){
                return RegistroDTO.builder()
                        .id((persona.getId()))
                        .nombre(persona.getNombre())
                        .usuario(usuario.getUsuario())
                        .correo(persona.getCorreo())
                        .telefono(persona.getTelefono())
                        .clave(usuario.getClave())
                        .build();
        }

}
