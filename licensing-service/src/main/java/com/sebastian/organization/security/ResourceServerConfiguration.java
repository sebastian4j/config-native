package com.sebastian.organization.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity hs) throws Exception {
//        hs.authorizeRequests().anyRequest().authenticated(); // todos los request tienen que ser autenticados
        hs.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/v1/**")
        .hasRole("ADMIN").anyRequest().authenticated();
    }
}
