package com.unit.whisper.external.kakao.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.Getter;

@Getter
public class KakaoAuthRequest {

    @JsonProperty("grant_type")
    private String grantType;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("redirect_uri")
    private String redirectUri;

    @JsonProperty("code")
    private String authCode;

    @JsonProperty("client_secret")
    private String clientSecret;

    public static Map<String, String> createKakaoAuthRequest(
            String clientId, String redirectUri, String authCode, String clientSecret) {
        return Map.of(
                "grant_type", "authorization_code",
                "client_id", clientId,
                "redirect_uri", redirectUri,
                "code", authCode,
                "client_secret", clientSecret);
    }
}
