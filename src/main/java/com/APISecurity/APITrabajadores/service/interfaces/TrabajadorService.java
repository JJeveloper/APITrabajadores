package com.APISecurity.APITrabajadores.service.interfaces;

import com.APISecurity.APITrabajadores.model.dto.ActualizarPasswordDTO;
import com.APISecurity.APITrabajadores.model.dto.TrabajadorDTO;

import java.util.List;


public interface TrabajadorService {

    TrabajadorDTO guardarTrabajador(TrabajadorDTO trabajadorDTO);

    String actualizarPasswordDTO(Integer id, ActualizarPasswordDTO trabajadorDTO);

    List<TrabajadorDTO> listarTrabajadores();

    void eliminar(int id);
}
