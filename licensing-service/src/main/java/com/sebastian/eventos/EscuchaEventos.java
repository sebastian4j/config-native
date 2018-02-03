package com.sebastian.eventos;

import org.apache.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 *
 * @author Sebastian Avila A.
 */
@EnableBinding(Sink.class)
public class EscuchaEventos {

    private static final Logger LOGGER = Logger.getLogger(EscuchaEventos.class);

    @StreamListener(Sink.INPUT)
    public void eventoRecibido(OrganizationEvent evento) {
        LOGGER.info("mensaje recibido: " + evento);
    }
}
