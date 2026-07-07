package com.APISecurity.APITrabajadores.controller;

import com.APISecurity.APITrabajadores.model.dto.ActualizarPasswordDTO;
import com.APISecurity.APITrabajadores.model.dto.TrabajadorDTO;
import com.APISecurity.APITrabajadores.service.interfaces.TrabajadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
@Tag(
        name = "Trabajadores",
        description = "API para administrar los trabajadores del sistema"
)
@SecurityRequirement(name = "Bearer Authentication")
//Le dice a Swagger que todos estos endpoints necesitan un JWT, para no colocar uno por uno
public class TrabajadorController {

    private TrabajadorService trabajadorService;

    @Autowired
    public TrabajadorController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    @Operation(
            summary = "Página de bienvenida",
            description = "Devuelve un mensaje de bienvenida para los usuarios que aún no han iniciado sesión."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Mensaje de bienvenida"
    )
    @GetMapping("inicio")
    public String inicioApi() {
        return "Bienvenido, por favor inicie sesion";
    }

    @Operation(
            summary = "Crear trabajador",
            description = "Registra un nuevo trabajador en el sistema."
    )
    @ApiResponses({

            @ApiResponse(
                    responseCode = "200",
                    description = "Trabajador creado correctamente"
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos"
            ),

            @ApiResponse(
                    responseCode = "401",
                    description = "No autenticado"
            ),

            @ApiResponse(
                    responseCode = "403",
                    description = "No autorizado"
            )

    })
    @PostMapping("/creartrabajador")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<TrabajadorDTO> guardarTrabajador(@Valid @RequestBody TrabajadorDTO trabajadorDTO) {
        return ResponseEntity.ok(trabajadorService.guardarTrabajador(trabajadorDTO));
    }


    @Operation(
            summary = "Actualizar contrasena",
            description = "Actualizar contrasena, validar contrasena anterior"
    )
    @ApiResponses({

            @ApiResponse(
                    responseCode = "200",
                    description = "Contrasena actualizada"
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos"
            ),

            @ApiResponse(
                    responseCode = "401",
                    description = "No autenticado"
            ),

            @ApiResponse(
                    responseCode = "403",
                    description = "No autorizado"
            )

    })
    @PutMapping("actualizarpassword/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<String> actualizarPassword(@PathVariable("id") Integer id, @Valid @RequestBody ActualizarPasswordDTO trabajadorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trabajadorService.actualizarPasswordDTO(id, trabajadorDTO));
    }

    @Operation(
            summary = "listar todos los trabajadores"
    )
    @ApiResponses({

            @ApiResponse(responseCode = "200",
                    description = "lista"),

            @ApiResponse(responseCode = "404",
                    description = "lista no encontrado")

    })
    @GetMapping("/listartrabajador")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<TrabajadorDTO>> listarTrabajadores() {
        return ResponseEntity.ok(trabajadorService.listarTrabajadores());
    }

    @Operation(
            summary = "Buscar trabajador por cédula"
    )
    @ApiResponses({

            @ApiResponse(responseCode = "200",
                    description = "Trabajador encontrado"),

            @ApiResponse(responseCode = "404",
                    description = "Trabajador no encontrado")

    })
    @GetMapping("/buscarcedula/{cedula}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<TrabajadorDTO> buscarPorCedula(@Parameter(
            description = "Número de cédula del trabajador",
            example = "0858741254"
    )
                                                         @PathVariable("cedula") String cedula) {

        return ResponseEntity.ok(trabajadorService.buscarPorCedula(cedula));
    }

    @Operation(
            summary = "Eliminar trabajador"
    )

    @ApiResponses({

            @ApiResponse(responseCode = "204",
                    description = "Trabajador eliminado"),

            @ApiResponse(responseCode = "404",
                    description = "No encontrado")

    })
    @DeleteMapping("/eliminartrabajador/{cedula}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarTrabajador(@PathVariable("cedula") String cedula) {
        trabajadorService.eliminar(cedula);
        return ResponseEntity.noContent().build();
    }

}
