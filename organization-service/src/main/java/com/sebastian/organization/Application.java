package com.sebastian.organization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import filter.UserContextFilter;


@SpringBootApplication
@ComponentScan(basePackages = {"filter", "com.sebastian"})
@EnableEurekaClient
//@EnableFeignClients
@EnableResourceServer
@EnableCircuitBreaker
public class Application {
    @Bean
    public UserContextFilter userFilter() {
        return new UserContextFilter();
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
