package com.sebastian.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class ServiceConfig {
    @Getter
    @Value("${app.nombre}")
    private String appNombre;
    @Getter
    @Value("${entorno:entorno}")
    private String entorno;
    @Getter
    @Value("${redis.server}")
    private String redisServer = "";
    @Getter
    @Value("${redis.port}")
    private int redisPort = 0;

    @PostConstruct
    private void post() {
        System.out.println(appNombre);
        System.out.println(entorno);
    }
}