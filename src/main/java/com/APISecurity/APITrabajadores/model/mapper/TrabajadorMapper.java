package com.APISecurity.APITrabajadores.model.mapper;


import com.APISecurity.APITrabajadores.model.dto.TrabajadorDTO;
import com.APISecurity.APITrabajadores.model.entity.TrabajadorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrabajadorMapper {

    TrabajadorEntity trabajadorDTOATrabajadorentity(TrabajadorDTO trabajadorDTO);

    TrabajadorDTO trabajadorEntityATrabajadorDTO(TrabajadorEntity trabajadorEntity);

}
