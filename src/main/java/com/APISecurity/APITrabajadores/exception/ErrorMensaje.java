package com.APISecurity.APITrabajadores.exception;

public class ErrorMensaje {

    private  String exception;
    private String message;
    private String ruta;

    public ErrorMensaje(Exception exception, String ruta) {
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.ruta = ruta;
    }

    public ErrorMensaje(Exception exception, String message, String ruta) {
        this.exception = exception.getClass().getSimpleName();
        this.message = message;
        this.ruta = ruta;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
