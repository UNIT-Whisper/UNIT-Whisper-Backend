package com.unit.whisper.entrypoint.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class GetNotificationListRequest {

    @Schema(description = "현재 자신의 위치 위도")
    private Double curMemberX;

    @Schema(description = "현재 자신의 위치 경도")
    private Double curMemberY;

    @Schema(description = "지도 화면 디스플레이의 좌상단 위도")
    private Double startX;

    @Schema(description = "지도 화면 디스플레이의 좌상단 경도")
    private Double endX;

    @Schema(description = "지도 화면 디스플레이의 우화단 위도")
    private Double startY;

    @Schema(description = "지도 화면 디스플레이의 우하단 경도")
    private Double endY;
}
