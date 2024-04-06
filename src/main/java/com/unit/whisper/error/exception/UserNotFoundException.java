package com.unit.whisper.error.exception;


import com.unit.whisper.error.ErrorCode;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND_ERROR);
    }
}
