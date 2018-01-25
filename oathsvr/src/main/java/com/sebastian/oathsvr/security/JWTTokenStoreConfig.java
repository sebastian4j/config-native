package com.sebastian.oathsvr.security;

import com.sebastian.oathsvr.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sebastian Avila A.
 */
@Configuration
@Component
public class JWTTokenStoreConfig {
    @Autowired
    private ServiceConfig sc;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(obtenerTokenConverter());
    }

    @Bean
    @Primary
    public DefaultTokenServices crearTokenServices() {
        DefaultTokenServices dts = new DefaultTokenServices();
        dts.setTokenStore(tokenStore());
        dts.setSupportRefreshToken(true);
        return dts;
    }

    @Bean
    public JwtAccessTokenConverter obtenerTokenConverter() {
        JwtAccessTokenConverter c = new JwtAccessTokenConverter();
        c.setSigningKey(sc.getKey());
        return c;
    }

    @Bean
    @Primary
    public TokenEnhancer crearEnhancer() {
        return new JWTTokenEnhancer();
    }
}
