package com.unit.whisper.entrypoint;


import com.unit.whisper.common.response.CustomResponseEntity;
import com.unit.whisper.entrypoint.request.GetNotificationListRequest;
import com.unit.whisper.entrypoint.response.NotificationWhisperListResponse;
import com.unit.whisper.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @Operation(summary = "알림 구름 목록 조회")
    @PostMapping("/list")
    public CustomResponseEntity<NotificationWhisperListResponse> getNotificationList(
            @RequestBody GetNotificationListRequest request) {
        return CustomResponseEntity.success(notificationService.getNotificationList(request));
    }
}
