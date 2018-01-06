package com.sebastian.repository;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sebastian.dominio.License;

@Repository
@ComponentScan
public interface LicenseRepository 
    extends CrudRepository<License, String> {
    
    List<License> findByOrganizationId(String organizationId);
    License findByOrganizationIdAndId(String organizationId, String Id);
}
