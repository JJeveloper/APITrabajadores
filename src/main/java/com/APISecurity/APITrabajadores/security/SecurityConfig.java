package com.APISecurity.APITrabajadores.security;

import com.APISecurity.APITrabajadores.security.filterjwt.JwtAuthenticationFilter;
import com.APISecurity.APITrabajadores.security.filterjwt.JwtAuthorizationFilter;
import com.APISecurity.APITrabajadores.security.jwt.JwtUtils;
import com.APISecurity.APITrabajadores.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private JwtUtils jwtUtils;
    private UserDetailsServiceImpl userDetailsService;
    private JwtAuthorizationFilter authorizationFilter;

    public SecurityConfig(JwtAuthorizationFilter authorizationFilter, JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService) {
        this.authorizationFilter = authorizationFilter;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {

        JwtAuthenticationFilter authenticationFilter = new JwtAuthenticationFilter(jwtUtils, authManager);

        http
                .csrf(csrf -> csrf.disable())//desactido para api rest, se usa jwt
                .authorizeHttpRequests(autorizar -> {
                    autorizar.requestMatchers("/login").permitAll();
                    autorizar.anyRequest().authenticated();
                })
                .addFilter(authenticationFilter)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    // 1. Bean para encriptar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAP = new DaoAuthenticationProvider();
        daoAP.setUserDetailsService(userDetailsService);
        daoAP.setPasswordEncoder(passwordEncoder());
        return daoAP;
    }

}
