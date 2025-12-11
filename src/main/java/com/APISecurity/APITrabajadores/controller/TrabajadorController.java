package com.APISecurity.APITrabajadores.controller;

import com.APISecurity.APITrabajadores.model.dto.ActualizarPasswordDTO;
import com.APISecurity.APITrabajadores.model.dto.TrabajadorDTO;
import com.APISecurity.APITrabajadores.service.interfaces.TrabajadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class TrabajadorController {

    private TrabajadorService trabajadorService;

    @Autowired
    public TrabajadorController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    @GetMapping("inicio")
    public String inicioApi() {
        return "Bienvenido, por favor inicie sesion";
    }

    @PostMapping("/creartrabajador")
    public ResponseEntity<TrabajadorDTO> guardarTrabajador(@Valid @RequestBody TrabajadorDTO trabajadorDTO) {
        return ResponseEntity.ok(trabajadorService.guardarTrabajador(trabajadorDTO));
    }

    @PutMapping("actualizarpassword/{id}")
    public ResponseEntity<String> actualizarPassword(@PathVariable("id") Integer id, @Valid @RequestBody ActualizarPasswordDTO trabajadorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trabajadorService.actualizarPasswordDTO(id, trabajadorDTO));
    }

    @GetMapping("/listartrabajador")
    public ResponseEntity<List<TrabajadorDTO>> listarTrabajadores() {
        return ResponseEntity.ok(trabajadorService.listarTrabajadores());
    }

    @GetMapping("/buscarcedula/{cedula}")
    public ResponseEntity<TrabajadorDTO> buscarPorCedula(@PathVariable("cedula") String cedula) {
        return ResponseEntity.ok(trabajadorService.buscarPorCedula(cedula));
    }

    @DeleteMapping("/eliminartrabajador/{cedula}")
    public ResponseEntity<Void> eliminarTrabajador(@PathVariable("cedula") String cedula) {
        trabajadorService.eliminar(cedula);
        return ResponseEntity.noContent().build();
    }

}
