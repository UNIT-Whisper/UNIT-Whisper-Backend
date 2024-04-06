package com.unit.whisper.util;

public class StaticValue {
    public static final String TOKEN_TYPE = "type";
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String REFRESH_TOKEN = "REFRESH_TOKEN";
    public static final String BEARER = "Bearer ";

    /*
    스웨거 패턴
     */
    public static final String[] SwaggerPatterns = {
        "/swagger-resources/**",
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/v3/api-docs",
        "/swagger-ui.html",
    };
}
