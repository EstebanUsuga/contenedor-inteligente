package com.reusoil.app.models.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UsuarioAPI {

    @NotBlank(message = "El usuario no puede estar vacío.")
    @Size(min = 3, max = 20, message = "El usuario debe tener entre 3 y 20 caracteres.")
    private String usuario;

    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    private String clave;

}
