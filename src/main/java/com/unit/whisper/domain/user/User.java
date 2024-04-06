package com.unit.whisper.domain.user;


import com.unit.whisper.domain.BaseEntity;
import com.unit.whisper.domain.whisper.Whisper;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long identityId;

    private String nickName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Whisper> whispers = new ArrayList<>();

    private User(Long identityId, String nickName) {
        this.identityId = identityId;
        this.nickName = nickName;
    }

    public static User toEntity(Long identityId, String nickName) {
        return new User(identityId, nickName);
    }

    /**
     * @title 구름 던지기
     * @param whisper
     */
    public void addWhisper(Whisper whisper) {
        this.getWhispers().add(whisper);
    }
}
