package com.unit.whisper.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "속삭임 생성 포맷")
public record WhisperCreateRequest(
        @NotBlank(message = "내용은 필수 입니다.") String content,
        @NotNull(message = "위도는 필수 입니다.") Double latitude,
        @NotNull(message = "경도는 필수 입니다.") Double longitude) {}
