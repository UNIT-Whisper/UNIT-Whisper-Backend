package com.unit.whisper.entrypoint.response;


import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WhisperResponse {
    @Schema(description = "구름 ID")
    private Long whisperId;

    @Schema(description = "구름 내용")
    private String content;

    @Schema(description = "구름 위치 위도")
    private Double latitude;

    @Schema(description = "구름 위치 경도")
    private Double longitude;

    @Schema(description = "구름 위치(도로명 주소)")
    private String address;

    @Schema(description = "구름 생성 일자")
    private LocalDateTime createDate;
}
