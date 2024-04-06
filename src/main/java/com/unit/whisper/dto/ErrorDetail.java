package com.unit.whisper.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDetail {

    private final Integer statusCode;
    private final int errorCode;
    private final String errorMessage;

    public static ErrorDetail of(Integer statusCode, int errorCode, String errorMessage) {
        return ErrorDetail.builder()
                .statusCode(statusCode)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }
}
