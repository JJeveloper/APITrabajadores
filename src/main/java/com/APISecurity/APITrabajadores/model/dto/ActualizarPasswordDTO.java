package com.APISecurity.APITrabajadores.model.dto;

import jakarta.validation.constraints.NotBlank;

public class ActualizarPasswordDTO {

    @NotBlank(message = "ingrese texto")
    private String anteriorpassword;


    private String nuevapassword;

    public ActualizarPasswordDTO() {
    }

    public String getAnteriorpassword() {
        return anteriorpassword;
    }

    public void setAnteriorpassword(String anteriorpassword) {
        this.anteriorpassword = anteriorpassword;
    }

    public String getNuevapassword() {
        return nuevapassword;
    }

    public void setNuevapassword(String nuevapassword) {
        this.nuevapassword = nuevapassword;
    }
}
