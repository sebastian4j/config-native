package com.sebastian.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.dominio.License;
import com.sebastian.services.LicenseService;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {
    @Autowired
    private LicenseService ls;
    
    @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
    public License getLicences(@PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) {        
        return ls.getLicense(organizationId, licenseId);
    }
}