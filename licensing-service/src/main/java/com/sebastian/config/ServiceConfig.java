package com.sebastian.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {
    @Value("${app.nombre}")
    private String appNombre;

    @Value("${entorno}")
    private String entorno;
    
    @PostConstruct
    private void post() {
        System.out.println(appNombre);
        System.out.println(entorno);
    }
}