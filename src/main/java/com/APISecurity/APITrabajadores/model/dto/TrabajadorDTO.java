package com.APISecurity.APITrabajadores.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class TrabajadorDTO {

    @NotBlank(message = "ingrese su cedula")
    @Pattern(regexp = "(0[1-9]|1[0-9]|2[0-4]|30)([0-9]{8})", message = " numero de cedula incorrecto")
    private String cedula;

    @Email
    private String email;

    @NotBlank(message = "ingrese sus nombres")
    private String nombres;

    @NotBlank(message = "ingrese su password")
    private String password;

    //@NotBlank(message = "ingrese un rol correcto")
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
