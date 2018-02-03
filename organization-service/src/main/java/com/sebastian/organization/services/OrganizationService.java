package com.sebastian.organization.services;

import com.sebastian.organization.eventos.SimpleResourceBean;
import com.sebastian.organization.model.Organization;
import com.sebastian.organization.repository.OrganizationRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository orgRepository;
    @Autowired
    private SimpleResourceBean rsr;

    public Organization getOrg(String organizationId) {
        rsr.publicar("select", organizationId);
        return orgRepository.findById(organizationId);
    }

    public void saveOrg(Organization org){
        rsr.publicar("insert", org.getId());
        org.setId(UUID.randomUUID().toString());
        orgRepository.save(org);
    }

    public void updateOrg(Organization org){
        rsr.publicar("update", org.getId());
        orgRepository.save(org);
    }

    public void deleteOrg(Organization org){
        rsr.publicar("delete", org.getId());
        orgRepository.delete(org.getId());
    }
}
