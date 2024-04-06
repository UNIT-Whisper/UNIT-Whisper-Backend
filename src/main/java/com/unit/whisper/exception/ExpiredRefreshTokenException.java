package com.unit.whisper.exception;


import com.unit.whisper.enumeration.ResultCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpiredRefreshTokenException extends BaseException {

    public ExpiredRefreshTokenException() {
        super(ResultCode.EXPIRED_REFRESH_TOKEN_ERROR);
    }
}
