package com.APISecurity.APITrabajadores.service.impl;

import com.APISecurity.APITrabajadores.repository.TrabajadorRepository;
import com.APISecurity.APITrabajadores.model.entity.TrabajadorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    TrabajadorRepository trabajadorRepository;

    @Autowired
    public UserDetailsServiceImpl(TrabajadorRepository trabajadorRepository) {
        this.trabajadorRepository = trabajadorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TrabajadorEntity trabajador = trabajadorRepository.findByCedula(username)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario" + username));

        String roles = trabajador.getRol().getRol();

        return new User(trabajador.getCedula(), trabajador.getPassword(),

                true,true,true,true,
                List.of(new SimpleGrantedAuthority("ROLE_" + roles)));

    }
}
