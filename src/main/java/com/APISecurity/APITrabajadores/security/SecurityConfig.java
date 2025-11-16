package com.APISecurity.APITrabajadores.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())//desactido para api rest, se usa jwt
                .authorizeHttpRequests(autorizar ->{
                    autorizar.requestMatchers("").permitAll();
                    autorizar.anyRequest().authenticated();
                });

        return null;
    }

    // 1. Bean para encriptar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder(){
    return  new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return  authenticationConfiguration.getAuthenticationManager();
    }
}
