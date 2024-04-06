package com.unit.whisper.domain.whisper;


import com.unit.whisper.domain.BaseEntity;
import javax.persistence.*;
import lombok.AccessLevel;
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
}
