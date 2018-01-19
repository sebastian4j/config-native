package com.sebastian.clients;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sebastian.dominio.Organization;

@Component
public class OrganizationRestTemplateClient {
    @Autowired
    private RestTemplate restTemplate;
    
    public Organization getOrganization(final String organizationId) {
        ResponseEntity<Organization> restExchange =
                restTemplate.exchange("http://organizationservice/v1/organizations/{organizationId}", 
                        HttpMethod.GET,
                        null, Organization.class, organizationId);
        return restExchange.getBody();
    }
}
