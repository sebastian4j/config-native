package com.sebastian.repository;

import com.sebastian.dominio.Organization;

/**
 *
 * @author Sebastian Avila A.
 */
public interface OrganizationRedisRepository {

    void saveOrganization(Organization org);

    void updateOrganization(Organization org);

    void deleteOrganization(String organizationId);

    Organization findOrganization(String organizationId);
}
