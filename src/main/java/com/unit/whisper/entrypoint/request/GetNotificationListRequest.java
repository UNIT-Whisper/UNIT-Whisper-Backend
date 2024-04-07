package com.unit.whisper.entrypoint.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class GetNotificationListRequest {

    @Schema(description = "현재 자신의 위치 위도")
    private Double curMemberX;

    @Schema(description = "현재 자신의 위치 경도")
    private Double curMemberY;
}
