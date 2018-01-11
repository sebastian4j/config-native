package com.sebastian.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.clients.OrganizationFeignClient;
import com.sebastian.dominio.License;
import com.sebastian.exception.LicenseNotFoundException;
import com.sebastian.services.LicenseService;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {
    private static final Logger LOGGER = Logger.getLogger(LicenseServiceController.class);
    @Autowired
    private LicenseService ls;
    @Autowired
    private OrganizationFeignClient ofc;
    
    @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
    public License getLicences(@PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) throws LicenseNotFoundException {
        LOGGER.info("org: " + ofc.getOrganization(organizationId));
        final License l = ls.getLicense(organizationId, licenseId);
        if (l != null) {
            return ls.getLicense(organizationId, licenseId);
        }
        else {
            throw new LicenseNotFoundException("error al obtener licensia", licenseId);
        }
    }
}