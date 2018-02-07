package com.sebastian.eventos;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 *
 * @author Sebastian Avila A.
 */
public interface CustomChannel {
    public static final String CANAL = "orgChannel";
    
    @Input(CANAL)
    SubscribableChannel orgs();
}
