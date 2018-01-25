package com.sebastian.oathsvr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * define las aplicaciones que se registran con el servicio de autenticacion.
 * @author sebastian
 *
 */
//@Configuration
public class OAuth2Config // extends AuthorizationServerConfigurerAdapter
{
    @Autowired
    private AuthenticationManager am;
    @Autowired
    private UserDetailsService uds;

    // clientes que pueden usar el servicio
//    @Override
    public void configure(ClientDetailsServiceConfigurer clt) throws Exception {
        clt.inMemory().withClient("eagleeye").secret("thisissecret")
                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                .scopes("webclient", "mobileclient");
    }

//    @Override
    public void configure(AuthorizationServerEndpointsConfigurer ep) throws Exception {
        ep.authenticationManager(am).userDetailsService(uds);
    }
}
