package com.unit.whisper.exception;


import com.unit.whisper.enumeration.ResultCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotValidAccessTokenException extends BaseException {

    private int code;
    private String message;

    public NotValidAccessTokenException() {
        super(ResultCode.NOT_VALID_ACCESS_TOKEN_ERROR);
        //        this.code = ResultCode.NOT_VALID_ACCESS_TOKEN_ERROR.getCode();
        //        this.message = ResultCode.NOT_VALID_ACCESS_TOKEN_ERROR.getMessage();
    }
}
