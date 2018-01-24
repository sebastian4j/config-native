package com.sebastian.licenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.sebastian.util.UserContextFilter;

@SpringBootApplication
@ComponentScan(basePackages = { "com.sebastian" })
@EntityScan(basePackages = { "com.sebastian.dominio" })
@EnableJpaRepositories("com.sebastian.repository")
@RefreshScope
@EnableDiscoveryClient // usando solo feignClient se puede omitir
@EnableFeignClients(basePackages = { "com.sebastian.clients" })
@EnableCircuitBreaker
@EnableResourceServer
public class Application {
    @Bean
    public UserContextFilter userFilter() {
        return new UserContextFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
