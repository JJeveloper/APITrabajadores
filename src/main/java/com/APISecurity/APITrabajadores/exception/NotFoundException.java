package com.APISecurity.APITrabajadores.exception;

public class NotFoundException extends RuntimeException{

    private static final String descripcion= "Not Found  Exception";

    public NotFoundException(String detalle) {
        super(descripcion + ", " + detalle);
    }
}
