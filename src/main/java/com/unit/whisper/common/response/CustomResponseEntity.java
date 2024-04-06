package com.unit.whisper.common.response;


import com.unit.whisper.enumeration.ResultCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomResponseEntity<T> {

    private int code;
    private String message;
    private T data;

    public static <T> CustomResponseEntity<T> success(T data) {
        return CustomResponseEntity.<T>builder()
                .code(ResultCode.OK.getCode())
                .message(ResultCode.OK.getMessage())
                .data(data)
                .build();
    }

    public static <T> CustomResponseEntity<T> success() {
        return CustomResponseEntity.<T>builder()
                .code(ResultCode.OK.getCode())
                .message(ResultCode.OK.getMessage())
                .build();
    }

    public static <T> CustomResponseEntity<T> fail() {
        return CustomResponseEntity.<T>builder()
                .code(ResultCode.FAIL.getCode())
                .message(ResultCode.FAIL.getMessage())
                .build();
    }

    public static <T> CustomResponseEntity<T> fail(ResultCode resultCode) {
        return CustomResponseEntity.<T>builder()
                .code(resultCode.getCode())
                .message(resultCode.getMessage())
                .build();
    }

    @Builder
    public CustomResponseEntity(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
