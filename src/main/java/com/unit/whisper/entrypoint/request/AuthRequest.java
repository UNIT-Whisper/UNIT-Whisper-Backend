package com.unit.whisper.entrypoint.request;


import lombok.Getter;

@Getter
public class AuthRequest {
    private String authCode;
    private String redirectUri;
}
