package com.APISecurity.APITrabajadores.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "trabajador")
public class TrabajadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtrabajador;

    private String cedula;

    private String email;

    private String nombres;

    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_idrol", referencedColumnName = "idrol")
    private RolEntity rolEntity;

    public TrabajadorEntity() {

    }

    public TrabajadorEntity(String cedula, String email, String nombres, String password, RolEntity rolEntity) {
        this.cedula = cedula;
        this.email = email;
        this.nombres = nombres;
        this.password = password;
        this.rolEntity = rolEntity;
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

    public Integer getIdtrabajador() {
        return idtrabajador;
    }

    public void setIdtrabajador(Integer idtrabajador) {
        this.idtrabajador = idtrabajador;
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

    public RolEntity getRolEntity() {
        return rolEntity;
    }

    public void setRolEntity(RolEntity rolEntity) {
        this.rolEntity = rolEntity;
    }
}
