package com.sabal.spring5webapp.security;

import com.sabal.spring5webapp.SpringApplicationContext;

public class SecurityConstants {

    public static final long EXPIRATION_TIME = 846000000;  // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";

    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
    }
}
