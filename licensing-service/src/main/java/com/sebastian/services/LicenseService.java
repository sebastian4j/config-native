package com.sebastian.services;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sebastian.dominio.License;
import com.sebastian.repository.LicenseRepository;

@Service
public class LicenseService {
    @Autowired
    private LicenseRepository lr;

    @PostConstruct
    private void post() {
        System.out.println(getClass().getName() + " postconstruct");
    }
    
    @HystrixCommand
    public License getLicense(String organizationId, String licenseId) {
        License license = lr.findByOrganizationIdAndId(organizationId, licenseId);           
        return license;
    }

    public List<License> getLicensesByOrg(String organizationId) {
        return lr.findByOrganizationId(organizationId);
    }

    public void saveLicense(License license) {        
        lr.save(license);
    }
}
