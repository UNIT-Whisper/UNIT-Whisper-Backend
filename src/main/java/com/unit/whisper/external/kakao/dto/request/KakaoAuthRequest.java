package com.unit.whisper.external.kakao.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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

    public static MultiValueMap<String, String> createKakaoAuthRequest(
            String clientId, String redirectUri, String authCode) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

        map.add("grant_type", "authorization_code");
        map.add("client_id", clientId);
        map.add("redirect_uri", redirectUri);
        map.add("code", authCode);
        return map;
    }
}
