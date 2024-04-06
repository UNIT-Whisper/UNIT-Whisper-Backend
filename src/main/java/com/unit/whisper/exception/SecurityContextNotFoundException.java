package com.unit.whisper.exception;


import com.unit.whisper.enumeration.ResultCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityContextNotFoundException extends BaseException {
    private int code;
    private String message;

    public SecurityContextNotFoundException() {
        super(ResultCode.SECURITY_CONTEXT_NOT_FOUND_ERROR);
    }
}
