package com.APISecurity.APITrabajadores.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String detalle) {
        super(detalle);
    }
}
