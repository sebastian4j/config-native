package com.sebastian.eventos;

import com.sebastian.repository.OrganizationRedisRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 *
 * @author Sebastian Avila A.
 */
@EnableBinding(CustomChannel.class)
//@EnableBinding(Sink.class)
public class EscuchaEventos {
    @Autowired
    private OrganizationRedisRepository orr;
    private static final Logger LOGGER = Logger.getLogger(EscuchaEventos.class);

//    @StreamListener(Sink.INPUT)
    @StreamListener(CustomChannel.CANAL)
    public void eventoRecibido(OrganizationEvent evento) {
        LOGGER.info("mensaje recibido: " + evento);
        try {
            LOGGER.info("en redis:" + orr.findOrganization(evento.getId()));
        } catch (Exception e) {
            LOGGER.info("error! " + e);
        }
    }
}
