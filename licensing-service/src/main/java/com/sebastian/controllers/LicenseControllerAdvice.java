package com.sebastian.controllers;

import java.util.Optional;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.exception.LicenseNotFoundException;

@ControllerAdvice(annotations = RestController.class)
public class LicenseControllerAdvice {
    private final MediaType mt = MediaType.parseMediaType("application/vnd.error");
    
    @ExceptionHandler(LicenseNotFoundException.class)
    ResponseEntity<VndErrors> notFoundException(LicenseNotFoundException e){
        return error(e, HttpStatus.NOT_FOUND, e.getErrorLicencia());
    }
    
    private <E extends Exception> ResponseEntity<VndErrors> error(E error,
            HttpStatus sts, String logRef) {
        String mgs = Optional.of(error.getMessage()).orElse(error.getClass().getSimpleName());
        HttpHeaders hdr = new HttpHeaders();
        hdr.setContentType(mt);        
        return new ResponseEntity<VndErrors>(new VndErrors(logRef, mgs), sts);
    }
}
