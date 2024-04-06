package com.unit.whisper.repository;


import com.unit.whisper.domain.notification.Notification;
import com.unit.whisper.domain.notification.NotificationStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByUserIdAndWhisperIdAndNotificationStatus(
            Long userId, Long whisperId, NotificationStatus notificationStatus);
}
