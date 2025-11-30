package com.APISecurity.APITrabajadores.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class APIExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            HttpRequestMethodNotSupportedException.class,
            MissingRequestHeaderException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
    })
    @ResponseBody
    public ErrorMensaje badRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMensaje(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    @ResponseBody
    public ErrorMensaje notFoundException(Exception exception, HttpServletRequest webRequest) {
        return new ErrorMensaje(exception, exception.getMessage(), webRequest.getRequestURI());
    }

    //errores en los campos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMensaje> validationExceptions(MethodArgumentNotValidException validException, HttpServletRequest webRequest) {
        Map<String, String> MapError = new HashMap<>();
        validException.getBindingResult().getAllErrors().forEach((error) -> {

                    String clave = ((FieldError) error).getField();
                    String valor = error.getDefaultMessage();
                    MapError.put(clave, valor);
                }
        );
        ErrorMensaje errorMensaje = new ErrorMensaje(validException, MapError.toString(), webRequest.getRequestURI());
        return new ResponseEntity<>(errorMensaje, HttpStatus.BAD_REQUEST);
    }


}
