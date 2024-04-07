package com.unit.whisper.service;


import com.unit.whisper.common.helper.UserHelper;
import com.unit.whisper.domain.notification.Notification;
import com.unit.whisper.domain.user.User;
import com.unit.whisper.domain.whisper.Whisper;
import com.unit.whisper.dto.WhisperCreateRequest;
import com.unit.whisper.entrypoint.response.WhisperResponse;
import com.unit.whisper.enumeration.ResultCode;
import com.unit.whisper.exception.BaseException;
import com.unit.whisper.external.ExternalClientProperties;
import com.unit.whisper.external.kakao.KakaoRestful;
import com.unit.whisper.repository.NotificationRepository;
import com.unit.whisper.repository.WhisperRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WhisperService {

    private final WhisperRepository whisperRepository;
    private final NotificationRepository notificationRepository;
    private final UserHelper userHelper;
    private final ExternalClientProperties properties;
    private final KakaoRestful kakaoRestful;

    @Transactional
    public Long saveWhisper(WhisperCreateRequest request) {
        User currentUser = userHelper.getCurrentUser();

        String address = kakaoRestful.getAddress(request.getLongitude(), request.getLatitude());
        log.info("address : {}", address);
        Whisper whisper =
                Whisper.toEntity(
                        currentUser.getId(),
                        request.getContent(),
                        request.getLatitude(),
                        request.getLongitude(),
                        address);

        whisperRepository.save(whisper);
        currentUser.addWhisper(whisper);

        return whisper.getId();
    }

    @Transactional
    public WhisperResponse getWhisper(Long whisperId, Long notificationId) {
        User currentUser = userHelper.getCurrentUser();
        Whisper whisper =
                whisperRepository
                        .findByIdAndUserId(whisperId, currentUser.getId())
                        .orElseThrow(() -> new BaseException(ResultCode.NOT_FOUND_WHISPER));

        // 해당 구름 알림 읽음 처리
        Notification notification =
                notificationRepository
                        .findByIdAndWhisperId(notificationId, whisperId)
                        .orElseThrow(() -> new BaseException(ResultCode.NOT_FOUND_NOTIFICATION));
        notification.read();

        return WhisperResponse.builder()
                .whisperId(whisper.getId())
                .content(whisper.getContent())
                .latitude(whisper.getLatitude())
                .longitude(whisper.getLongitude())
                .address(whisper.getAddress())
                .createDate(whisper.getCreatedAt())
                .build();
    }
}
