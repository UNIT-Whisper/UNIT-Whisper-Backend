package com.unit.whisper.domain.whisper;


import com.unit.whisper.domain.BaseEntity;
import com.unit.whisper.domain.user.User;
import javax.persistence.*;
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

    private String title;

    private String content;

    private Double latitude;

    private Double longitude;

    @Embedded private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    private Whisper(String content, Double latitude, Double longitude, User user, Address address) {
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
        this.address = address;
    }
}
