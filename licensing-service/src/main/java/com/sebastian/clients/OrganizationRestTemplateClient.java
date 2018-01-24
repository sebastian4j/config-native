package com.sebastian.clients;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sebastian.dominio.Organization;

@Component
public class OrganizationRestTemplateClient {
    @Autowired
    private RestTemplate restTemplate;
//    @Autowired
//    private OAuth2RestTemplate ort;
    
    public Organization getOrganization(final String organizationId) {
//        System.out.println("TOKEN:: " + ort.getAccessToken().getTokenType());
        
        ResponseEntity<Organization> restExchange =
//                ort.exchange("http://zuulservice:5555/api/organization/v1/organizations/{organizationId}", 
//                        HttpMethod.GET,
//                        null, Organization.class, organizationId);
                restTemplate.exchange("http://organizationservice/v1/organizations/{organizationId}", 
                HttpMethod.GET,
                null, Organization.class, organizationId);
        return restExchange.getBody();
    }
}
