package com.unit.whisper.entrypoint.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class AuthRequest {
    @Schema(description = "인가 코드")
    private String authCode;

    @Schema(description = "인가 코드")
    private String redirectUri;
}
