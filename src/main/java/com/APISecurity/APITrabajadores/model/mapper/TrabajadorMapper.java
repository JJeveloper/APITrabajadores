package com.APISecurity.APITrabajadores.model.mapper;


import com.APISecurity.APITrabajadores.model.dto.TrabajadorDTO;
import com.APISecurity.APITrabajadores.model.entity.RolEntity;
import com.APISecurity.APITrabajadores.model.entity.TrabajadorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TrabajadorMapper {

    @Mapping(source = "rolEntity.rol", target = "rol")
    TrabajadorDTO trabajadorEntityATrabajadorDTO(TrabajadorEntity trabajadorEntity);

    TrabajadorEntity trabajadorDTOATrabajadorentity(TrabajadorDTO trabajadorDTO);

    List<TrabajadorDTO> trabajadorDTOList(List<TrabajadorEntity> trabajadorEntityList);


}
