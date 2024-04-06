package com.unit.whisper.enumeration;


import lombok.Getter;

@Getter
public enum ResultCode {
    OK(0, "성공"),
    FAIL(-1, "실패"),
    NOT_FOUND_USER(1000, "존재하지 않는 사용자입니다."),

    NOT_FOUND_WHISPER(2000, "존재하지 않는 구름입니다."),

    EXPIRED_TOKEN_ERROR(4000, "토큰이 만료되었습니다."),
    INVALID_TOKEN_ERROR(4001, "올바르지 않은 토큰입니다."),
    EXPIRED_REFRESH_TOKEN_ERROR(4002, "리프레시 토큰이 만료되었습니다."),
    NOT_VALID_ACCESS_TOKEN_ERROR(4003, "알맞은 accessToken 을 넣어주세요."),
    SECURITY_CONTEXT_NOT_FOUND_ERROR(4004, "Security Context 에러입니다.");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
