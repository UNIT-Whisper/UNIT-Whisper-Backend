package com.unit.whisper.external.kakao.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KakaoAuthPayload {
    @JsonProperty("bearer")
    private String tokenType;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("refresh_token_expires_in")
    private int refreshTokenExpiresIn;

    private String scope;
}
