package com.unit.whisper.entrypoint.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {
    private Long id;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpireTime;

    public static AuthResponse of(
            Long id, String accessToken, String refreshToken, Long accessTokenExpireTime) {
        return AuthResponse.builder()
                .id(id)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpireTime(accessTokenExpireTime)
                .build();
    }
}
