package com.unit.whisper.entrypoint.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {

    @Schema(description = "로그인한 사용자 ID")
    private Long id;

    @Schema(description = "액세스 토큰")
    private String accessToken;

    @Schema(description = "리프레시 토큰")
    private String refreshToken;

    @Schema(description = "액세스 토큰의 유효 기간(초 단위, 1시간)")
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
