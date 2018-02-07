package com.sebastian.repository;

import com.sebastian.dominio.Organization;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sebastian Avila A.
 */
@Repository
public class OrganizationRedisRepositoryLocal implements
        OrganizationRedisRepository {
    private static final Logger LOGGER =
            Logger.getLogger(OrganizationRedisRepositoryLocal.class);

    private static final String HASH_NAME = "organization";
    private HashOperations ho;
    private RedisTemplate<String, Organization> rt;

    @Autowired
    public OrganizationRedisRepositoryLocal(RedisTemplate rt) {
        LOGGER.debug("redis template:: " + rt);
        this.rt = rt;
    }

    @PostConstruct
    private void init() {
        ho = rt.opsForHash();
        LOGGER.debug("init:: " + ho);
    }

    @Override
    public void saveOrganization(Organization org) {
        LOGGER.info("save-organization " + ho);
        ho.put(HASH_NAME, org.getId(), org);
    }

    @Override
    public void updateOrganization(Organization org) {
        LOGGER.info("update-organization");
        ho.put(HASH_NAME, org.getId(), org);
    }

    @Override
    public void deleteOrganization(String organizationId) {
        LOGGER.info("delete-organization");
        ho.delete(HASH_NAME, organizationId);
    }

    @Override
    public Organization findOrganization(String organizationId) {
        LOGGER.info("find-organization");
        return (Organization)ho.get(HASH_NAME, organizationId);
    }

}
