package com.unit.whisper.entrypoint.response;


import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationWhisperListResponse {

    private List<NotificationWhisper> NotificationWhispers;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NotificationWhisper {
        @Schema(description = "알림 ID")
        private Long notificationId;

        @Schema(description = "알림 내용")
        private final String content = "근처에 스쳐간 구름이 하나 있어요. <br>확인해보시겠어요?";

        @Schema(description = "알림 구름의 위치(도로명 주소)")
        private String address;

        @Schema(description = "구름 ID")
        private Long whisperId;

        @Schema(description = "알림 생성 일자")
        private LocalDateTime createDate;
    }
}
