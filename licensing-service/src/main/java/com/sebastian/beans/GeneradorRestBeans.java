package com.sebastian.beans;

import com.sebastian.util.UserContextInterceptor;
import java.util.Collections;
import java.util.List;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

//    @Bean
//    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext,
//            OAuth2ProtectedResourceDetails details) {
//        return new OAuth2RestTemplate(details, oauth2ClientContext);
//    }
}
