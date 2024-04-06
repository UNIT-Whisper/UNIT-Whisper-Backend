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

    private String email;

    private String nickName;

    private User(String email, String nickName) {
        this.email = email;
        this.nickName = nickName;
    }

    public static User toEntity(String email, String nickName) {
        return new User(email, nickName);
    }
}
