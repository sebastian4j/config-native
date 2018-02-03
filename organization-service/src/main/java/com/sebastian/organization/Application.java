package com.sebastian.organization;

import filter.UserContextFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@ComponentScan(basePackages = {"filter", "com.sebastian"})
@EnableEurekaClient
//@EnableFeignClients
@EnableResourceServer
@EnableCircuitBreaker
@EnableBinding(Source.class)
public class Application {
    @Bean
    public UserContextFilter userFilter() {
        return new UserContextFilter();
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
