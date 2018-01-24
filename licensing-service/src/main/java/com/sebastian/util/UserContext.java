package com.sebastian.util;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserContext {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN = "tmx-auth-token";
    public static final String USER_ID = "tmx-user-id";
    public static final String ORG_ID = "tmx-org-id";
    public static final String AUTHORIZATION = "Authorization";

    private String correlationId = new String();
    private String authToken = new String();
    private String userId = new String();
    private String orgId = new String();
    private String authorization = new String();

}
