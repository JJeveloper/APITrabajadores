package com.APISecurity.APITrabajadores.model.dto;

import com.APISecurity.APITrabajadores.model.entity.RolEntity;

public class TrabajadorDTO {


    private String cedula;

    private String email;

    private String nombres;

    private String password;

    String rol;

    public TrabajadorDTO() {
    }

    public TrabajadorDTO(String cedula, String email, String nombres, String password, String rol) {
        this.cedula = cedula;
        this.email = email;
        this.nombres = nombres;
        this.password = password;
        this.rol = rol;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
