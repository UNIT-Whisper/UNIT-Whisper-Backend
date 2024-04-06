package com.unit.whisper.exception;


import com.unit.whisper.enumeration.ResultCode;
import lombok.Getter;

@Getter
public class ExpiredTokenException extends BaseException {
    private int code;
    private String message;

    public ExpiredTokenException() {
        super(ResultCode.EXPIRED_TOKEN_ERROR);
    }
}
