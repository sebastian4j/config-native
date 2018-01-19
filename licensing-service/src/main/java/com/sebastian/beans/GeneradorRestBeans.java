package com.sebastian.beans;

import java.util.Collections;
import java.util.List;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sebastian.util.UserContextInterceptor;

@Component
public class GeneradorRestBeans {
    
    @LoadBalanced
    @Bean
    public RestTemplate generarRestTemplate() {
        final RestTemplate rt = new RestTemplate();
        List<ClientHttpRequestInterceptor> inter = rt.getInterceptors();
        if (inter == null) {
            rt.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        } else {
            inter.add(new UserContextInterceptor());           
        }
        return rt;
    }
}
