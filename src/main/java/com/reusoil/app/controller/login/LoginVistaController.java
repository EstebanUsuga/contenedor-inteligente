package com.reusoil.app.controller.login;

import com.reusoil.app.models.registro.RegistroDTO;
import com.reusoil.app.models.usuario.UsuarioEntity;
import com.reusoil.app.services.usuario.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginVistaController {

    private final UsuarioServiceImpl usuarioService;

    @GetMapping("/mostrar-login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("registroDTO", new RegistroDTO());
        model.addAttribute("usuarioLogin", new UsuarioEntity());
        return "vistas/inicio/login";
    }

    @GetMapping("/home")
    public String mostrarHomepage() {
        return "vistas/homepage";
    }


}
