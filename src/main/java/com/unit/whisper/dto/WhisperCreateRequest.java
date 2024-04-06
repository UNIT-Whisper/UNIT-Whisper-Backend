package com.unit.whisper.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "속삭임 생성 포맷")
public class WhisperCreateRequest {

    @NotBlank(message = "내용은 필수 입니다.")
    private String content;

    @NotNull(message = "위도는 필수 입니다.")
    private Double latitude;

    @NotNull(message = "경도는 필수 입니다.")
    private Double longitude;
}
