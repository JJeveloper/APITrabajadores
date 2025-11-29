package com.APISecurity.APITrabajadores.service.interfaces;

import com.APISecurity.APITrabajadores.model.dto.TrabajadorDTO;

import java.util.List;


public interface TrabajadorService {

    TrabajadorDTO guardarTrabajador(TrabajadorDTO trabajadorDTO);

    List<TrabajadorDTO> listarTrabajadores();
}
