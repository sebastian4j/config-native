package com.sebastian.oathsvr;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer // recurso protegido por oath2
@EnableAuthorizationServer // habilita oath2
public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class);
    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(value = {"/user"}, produces = "application/json")
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();        
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        LOGGER.info("rest-user-info: " + userInfo);
        return userInfo;
    }
}
