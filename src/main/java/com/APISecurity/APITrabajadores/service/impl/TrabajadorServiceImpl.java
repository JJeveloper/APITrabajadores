package com.APISecurity.APITrabajadores.service.impl;

import com.APISecurity.APITrabajadores.exception.NotFoundException;
import com.APISecurity.APITrabajadores.model.dto.TrabajadorDTO;
import com.APISecurity.APITrabajadores.model.entity.RolEntity;
import com.APISecurity.APITrabajadores.model.entity.TrabajadorEntity;
import com.APISecurity.APITrabajadores.model.mapper.TrabajadorMapper;
import com.APISecurity.APITrabajadores.repository.RolRepository;
import com.APISecurity.APITrabajadores.repository.TrabajadorRepository;
import com.APISecurity.APITrabajadores.service.interfaces.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

    private TrabajadorRepository trabajadorRepository;
    private TrabajadorMapper trabajadorMapper;
    private PasswordEncoder passwordEncoder;
    private RolRepository rolRepository;

    @Autowired
    public TrabajadorServiceImpl(TrabajadorRepository trabajadorRepository, RolRepository rolRepository, TrabajadorMapper trabajadorMapper, PasswordEncoder passwordEncoder) {
        this.trabajadorRepository = trabajadorRepository;
        this.rolRepository = rolRepository;
        this.trabajadorMapper = trabajadorMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public TrabajadorDTO guardarTrabajador(TrabajadorDTO trabajadorDTO) {

        TrabajadorEntity trabajadorEntity = trabajadorMapper.trabajadorDTOATrabajadorentity(trabajadorDTO);
        trabajadorEntity.setPassword(passwordEncoder.encode(trabajadorDTO.getPassword()));

        RolEntity rol = rolRepository.findByRol(trabajadorDTO.getRol()).
                orElseThrow(() -> {
                    return new NotFoundException("No existe el rol, ingrese uno correcto");
                });

        trabajadorEntity.setRolEntity(rol);

        return trabajadorMapper.trabajadorEntityATrabajadorDTO(trabajadorRepository.save(trabajadorEntity));
    }

    @Override
    public List<TrabajadorDTO> listarTrabajadores() {
        return null;
    }
}
