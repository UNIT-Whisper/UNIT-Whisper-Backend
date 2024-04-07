package com.unit.whisper.service;


import com.unit.whisper.common.helper.UserHelper;
import com.unit.whisper.domain.notification.Notification;
import com.unit.whisper.domain.notification.NotificationStatus;
import com.unit.whisper.domain.user.User;
import com.unit.whisper.domain.whisper.Whisper;
import com.unit.whisper.entrypoint.request.GetNotificationListRequest;
import com.unit.whisper.entrypoint.response.NotificationWhisperListResponse;
import com.unit.whisper.repository.NotificationRepository;
import com.unit.whisper.repository.WhisperRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final WhisperRepository whisperRepository;
    private final UserHelper userHelper;

    public NotificationWhisperListResponse getNotificationList(GetNotificationListRequest request) {

        /**
         * 현재 유저의 위치와 구름과의 거리가 100m 반경이 되면서 + 해당 구름의 최근 알림이 울린지 24시간이 지났을 때,
         *
         * <p>-> 알림으로 등록하고 -> 알림으로 내려주기
         *
         * <p>-> 알림 목록 조회 API 의 응답으로는, 이전에 쌓인 알람들도 볼 수 있도록 UNREAD 인 상태의 모든 알림 조회
         */
        User currentUser = userHelper.getCurrentUser();

        // 구름을 -> 알림으로 발행
        List<Whisper> withinRadius =
                whisperRepository.findWithinRadius(
                        request.getCurMemberX(), request.getCurMemberY(),
                        request.getStartX(), request.getEndX(),
                        request.getStartY(), request.getEndY());
        //                LocalDateTime.now(ZoneId.of("Asia/Seoul"))

        // 이전에 알림으로 설정되어 있지 않은 경어에 대해서 알림으로 추가
        withinRadius.forEach(
                whisper -> {
                    Notification notification =
                            Notification.toEntity(
                                    whisper.getAddress(), whisper.getUserId(), whisper.getId());
                    notificationRepository.save(notification);
                    notificationRepository.flush();
                });

        // 알림 조회 로직
        List<NotificationWhisperListResponse.NotificationWhisper> list = new ArrayList<>();

        withinRadius.forEach(
                whisper -> {
                    List<Notification> notifications =
                            notificationRepository.findAllByUserIdAndWhisperIdAndNotificationStatus(
                                    currentUser.getId(),
                                    whisper.getId(),
                                    NotificationStatus.UNREAD);

                    notifications.forEach(
                            notification -> {
                                NotificationWhisperListResponse.NotificationWhisper
                                        notificationWhisper =
                                                new NotificationWhisperListResponse
                                                        .NotificationWhisper(
                                                        notification.getId(),
                                                        notification.getAddress(),
                                                        notification.getWhisperId(),
                                                        notification.getCreatedAt());
                                list.add(notificationWhisper);
                            });

                    // whisper 의 가장 최근 알림 일자 변경
                    whisper.updateLastNotificationDateTime(
                            LocalDateTime.now(ZoneId.of("Asia/Seoul")));
                });

        return NotificationWhisperListResponse.builder().NotificationWhispers(list).build();
    }
}
