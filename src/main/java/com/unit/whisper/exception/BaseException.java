package com.unit.whisper.exception;


import com.unit.whisper.enumeration.ResultCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {

    private int code;
    private String message;

    public BaseException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }
}
