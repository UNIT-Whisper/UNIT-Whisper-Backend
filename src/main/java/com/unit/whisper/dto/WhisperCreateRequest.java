package com.unit.whisper.dto;


import com.unit.whisper.domain.whisper.Whisper;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Schema(description = "속삭임 생성 포맷")
public record WhisperCreateRequest(
        @NotBlank(message = "내용은 필수 입니다.") String content,
        @NotNull(message = "위도는 필수 입니다.") Double latitude,
        @NotNull(message = "경도는 필수 입니다.") Double longitude) {

    public Whisper toEntity(Long userId) {
        return Whisper.builder()
                .userId(userId)
                .content(content)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
