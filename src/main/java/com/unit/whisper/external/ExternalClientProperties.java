package com.unit.whisper.external;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties("client")
public class ExternalClientProperties {

    private final Kakao kakao;

    @Getter
    @AllArgsConstructor
    public static class Kakao {
        private final String clientId;
        private final String clientSecret;

        private final String authBaseurl;
        private final String authRedirectUri;
        private final String authLocalRedirectUri;

        private final String apiBaseUrl;
    }
}
