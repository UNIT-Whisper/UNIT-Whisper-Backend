package com.unit.whisper.error.exception;


import com.unit.whisper.error.ErrorCode;

public class KakaoMapException extends BusinessException {

    public KakaoMapException() {
        super(ErrorCode.KAKAO_MAP_API_ERROR);
    }
}
