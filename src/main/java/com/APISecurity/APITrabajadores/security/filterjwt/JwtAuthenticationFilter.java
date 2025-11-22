package com.APISecurity.APITrabajadores.security.filterjwt;

import com.APISecurity.APITrabajadores.model.entity.TrabajadorEntity;
import com.APISecurity.APITrabajadores.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//Authentication (login con JSON + JWT)
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {

            TrabajadorEntity trabajador = new ObjectMapper().readValue(request.getInputStream(), TrabajadorEntity.class);
            String cedula = trabajador.getCedula();
            String password = trabajador.getPassword();

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(cedula, password);

            return authenticationManager.authenticate(authenticationToken);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();
        String token = jwtUtils.generarToken(user.getUsername());

        Map<String, Object> body = new HashMap<>();
        body.put("Token",token);
        body.put("Usuario", user.getUsername());

        body.put("Mensaje", "Autenticacion correcta");

        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getWriter(), body);
    }
}
