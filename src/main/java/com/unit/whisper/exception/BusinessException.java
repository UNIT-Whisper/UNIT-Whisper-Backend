package com.unit.whisper.exception;


import com.unit.whisper.error.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());

        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode.getMessage(), throwable);

        this.errorCode = errorCode;
    }
}
