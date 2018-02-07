package com.sebastian.clients;

import com.sebastian.dominio.Organization;
import com.sebastian.repository.OrganizationRedisRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrganizationRestTemplateClient {

    private static final Logger LOGGER
            = Logger.getLogger(OrganizationRestTemplateClient.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrganizationRedisRepository orr;
//    @Autowired
//    private OAuth2RestTemplate ort;

    private Organization checkRedisCache(String id) {
        LOGGER.info("buscando en redis... " + id + " orr: " + orr);
        Organization org = null;
        try {
            org = orr.findOrganization(id);
        } catch (Exception e) {
            LOGGER.error("error al buscar en redis", e);
        }
        if (org == null) {
            LOGGER.info("no encontrado...");
        } else {
            LOGGER.info("encontrado en redis!! " + org);
        }
        return org;
    }

    private void guardarEnRedis(Organization org) {
        LOGGER.info("guardando en redis " + org + " " + orr);
        try {
            orr.saveOrganization(org);
        } catch (Exception e) {
            LOGGER.error("error al guardar en redis", e);
        }
    }

    public Organization getOrganization(final String organizationId) {
//        System.out.println("TOKEN:: " + ort.getAccessToken().getTokenType());
        Organization org = checkRedisCache(organizationId);

        if (org == null) {
            LOGGER.info("no encontrado en redis...");
            ResponseEntity<Organization> restExchange
                    = //                ort.exchange("http://zuulservice:5555/api/organization/v1/organizations/{organizationId}",
                    //                        HttpMethod.GET,
                    //                        null, Organization.class, organizationId);
                    restTemplate.exchange("http://organizationservice/v1/organizations/{organizationId}",
                            HttpMethod.GET,
                            null, Organization.class, organizationId);
            org = restExchange.getBody();
        } else {
            LOGGER.info("encontrado en redis " + org);
        }
        if (org != null) {
            guardarEnRedis(org);
            LOGGER.info("almacenado en redis " + org);
        }
        return org;
    }
}
