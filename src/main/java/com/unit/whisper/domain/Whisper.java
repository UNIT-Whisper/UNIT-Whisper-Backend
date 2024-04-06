package com.unit.whisper.domain;


import jakarta.persistence.*;
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
