package com.unit.whisper.domain.user;


import com.unit.whisper.domain.BaseEntity;
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

    private User(Long identityId, String nickName) {
        this.identityId = identityId;
        this.nickName = nickName;
    }

    public static User toEntity(Long identityId, String nickName) {
        return new User(identityId, nickName);
    }
}
