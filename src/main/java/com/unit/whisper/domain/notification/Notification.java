package com.unit.whisper.domain.notification;


import com.unit.whisper.domain.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "notification")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @Enumerated(EnumType.STRING)
    @NotNull
    private NotificationStatus notificationStatus;

    private Long userId;
    private Long whisperId;

    public void read() {
        this.notificationStatus = NotificationStatus.READ;
    }

    private Notification(String address, Long userId, Long whisperId) {
        this.address = address;
        this.userId = userId;
        this.whisperId = whisperId;
        this.notificationStatus = NotificationStatus.UNREAD;
    }

    public static Notification toEntity(String address, Long userId, Long whisperId) {
        return new Notification(address, userId, whisperId);
    }
}
