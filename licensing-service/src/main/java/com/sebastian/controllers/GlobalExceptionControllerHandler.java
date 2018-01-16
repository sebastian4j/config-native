package com.sebastian.controllers;

import com.sebastian.dominio.EstadoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Sebastian Avila A.
 */
@RestControllerAdvice
public class GlobalExceptionControllerHandler {

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public EstadoServicio unknownException(Exception ex, WebRequest req) {
        final EstadoServicio es = new EstadoServicio();
        es.setCodigo(3);
        es.setDescripcion(ex.getMessage());
        return es;
    }
}