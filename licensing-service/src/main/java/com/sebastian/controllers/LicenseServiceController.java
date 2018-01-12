package com.sebastian.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
            throw new LicenseNotFoundException("error al obtener licencia", licenseId);
        }
    }
    
    @HystrixCommand(fallbackMethod = "fallBack")
    @RequestMapping(value = "/{licenseId}/bd", method = RequestMethod.GET)
    public License getLicencesBD(@PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) throws LicenseNotFoundException {
        try {
            Thread.sleep(4000);
        } catch(Exception e) { e.printStackTrace();} 
        final License l = ls.getLicense(organizationId, licenseId);
        if (l != null) {
            return ls.getLicense(organizationId, licenseId);
        }
        else {
            throw new LicenseNotFoundException("error al obtener licencia", licenseId);
        }
    }    
    
    public License fallBack(String organizationId, String licenseId) throws LicenseNotFoundException {
        final License l = new License();
        l.setId("0");
        l.setLicenceType("0");
        l.setOrganizationId("0");
        l.setProductName("sin servicio de licencias");
        return l;
    }
}