package com.unit.whisper.dto;


import lombok.Getter;

@Getter
public class ErrorResponse {

    private final boolean success = false;
    private final int errorCode;
    private final String errorMessage;

    public ErrorResponse(ErrorDetail errorDetail) {
        this.errorCode = errorDetail.getErrorCode();
        this.errorMessage = errorDetail.getErrorMessage();
    }
}
