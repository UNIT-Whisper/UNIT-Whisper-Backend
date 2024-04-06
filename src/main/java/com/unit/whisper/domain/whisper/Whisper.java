package com.unit.whisper.domain.whisper;


import com.unit.whisper.domain.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "whisper")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Whisper extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Double latitude;

    private Double longitude;

    @NotNull private Long userId;

    private String address;

    private LocalDateTime lastNotificationDateTime;

    public static Whisper toEntity(
            Long userId, String content, Double latitude, Double longitude, String address) {
        return new Whisper(userId, content, latitude, longitude, address);
    }

    @Builder
    private Whisper(
            Long userId, String content, Double latitude, Double longitude, String address) {
        this.userId = userId;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public void updateLastNotificationDateTime(LocalDateTime lastNotificationDateTime) {
        this.lastNotificationDateTime = lastNotificationDateTime;
    }
}
