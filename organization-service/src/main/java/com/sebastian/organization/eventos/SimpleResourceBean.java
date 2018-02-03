package com.sebastian.organization.eventos;

import filter.UserContextHolder;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sebastian Avila A.
 */
@Component
public class SimpleResourceBean {
    private final Source source;
    private static final Logger LOGGER = Logger.getLogger(SimpleResourceBean.class);

    @Autowired
    public SimpleResourceBean(final Source src) {
        source = src;
    }

    public void publicar(final String action, final String id) {
        LOGGER.debug(">>action: " + action);
        LOGGER.debug(">>id: " + id);
        OrganizationEvent ev = new OrganizationEvent();
        ev.setType(OrganizationEvent.class.getTypeName());
        ev.setAction(action);
        ev.setId(id);
        ev.setCorrelation(UserContextHolder.getContext().getCorrelationId());
        LOGGER.debug(">>ev: " + ev);
        source.output().send(
                MessageBuilder.withPayload(ev).build());
    }

}
