package com.APISecurity.APITrabajadores.controller;

import com.APISecurity.APITrabajadores.model.dto.TrabajadorDTO;
import com.APISecurity.APITrabajadores.service.interfaces.TrabajadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class TrabajadorController {

    private TrabajadorService trabajadorService;

    @Autowired
    public TrabajadorController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    @GetMapping("inicio")
    public String inicioApi(){
        return "Bienvenido, por favor inicie sesion";
    }

    @PostMapping("/creartrabajador")
    public ResponseEntity<TrabajadorDTO> guardarTrabajador(@Valid @RequestBody TrabajadorDTO trabajadorDTO) {
        return ResponseEntity.ok(trabajadorService.guardarTrabajador(trabajadorDTO));
    }

}
