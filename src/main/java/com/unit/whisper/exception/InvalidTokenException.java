package com.unit.whisper.exception;


import com.unit.whisper.enumeration.ResultCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidTokenException extends BaseException {

    private int code;
    private String message;

    public InvalidTokenException() {
        super(ResultCode.INVALID_TOKEN_ERROR);
    }
}
