package com.APISecurity.APITrabajadores.exception;

public class BadRequestException extends RuntimeException{
    private static final String descripcion= "Bad Request Exception";

    public BadRequestException(String detalle) {
        super(descripcion  + ", " + detalle);
    }
}
