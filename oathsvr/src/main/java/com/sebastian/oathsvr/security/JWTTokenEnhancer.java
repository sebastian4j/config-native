package com.sebastian.oathsvr.security;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 *
 * @author Sebastian Avila A.
 */
public class JWTTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> adicional = new HashMap<>();
        adicional.put("saludo", "¡¡Hola!!");
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(adicional);
        return accessToken;
    }

}
