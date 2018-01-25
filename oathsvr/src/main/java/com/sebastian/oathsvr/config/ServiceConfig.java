package com.sebastian.oathsvr.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sebastian Avila A.
 */
@Data
@Component
@Configuration
public class ServiceConfig {
    @Value("${signing.key}")
    private String key;

}
