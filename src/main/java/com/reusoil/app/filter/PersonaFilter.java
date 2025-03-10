package com.reusoil.app.filter;

import com.reusoil.app.models.persona.PersonaEntity;
import com.reusoil.app.services.persona.PersonaService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

//@Component //Comentar si no se va a usar
@RequiredArgsConstructor
public class PersonaFilter implements Filter {

    private final PersonaService personaService;

    public static final String MOSTRAR_LOGIN_URI = "/mostrar-login";
    public static final String LOGIN_URI = "/login";
    public static final String EMPRESA_GUARDAR_URI = "/empresa/guardar-empresa";

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
        //Acá casteamos el request
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //Acá recuperamos la sesión sin crear una nueva
        HttpSession session = httpRequest.getSession(false);
        String requestURI = httpRequest.getRequestURI();

        if(estaSinRestricciones(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        if(Objects.isNull(session)) {
            httpResponse.sendRedirect(MOSTRAR_LOGIN_URI);
            return;
        }

        Long usuarioId = (Long) session.getAttribute("usuarioId");
        Optional<PersonaEntity> persona = personaService.obtenerPersonaPorUsuarioId(usuarioId);

        if (persona.isPresent() && !httpRequest.getRequestURI().equals("/empresa/seleccionar-empresa")) {
            if (persona.get().getEmpresa() == null){
                httpResponse.sendRedirect("/empresa/seleccionar-empresa");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private static boolean estaSinRestricciones(String requestURI) {
        return Stream.of("/css/", "/js/", "/img/", "/static/", EMPRESA_GUARDAR_URI, LOGIN_URI, MOSTRAR_LOGIN_URI)
                .anyMatch(requestURI::startsWith);
    }
}

