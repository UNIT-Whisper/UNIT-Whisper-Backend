package com.unit.whisper.external.kakao.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KakaoUserInfoPayload {

    private long id;
    private KakaoAccount kakaoAccount;

    @Getter
    public static class KakaoAccount {
        private Profile profile;

        @Getter
        public static class Profile {
            private String nickname;

            @JsonProperty("thumbnail_image_url")
            private String thumbnailImageUrl;

            @JsonProperty("profile_image_url")
            private String profileImageUrl;
        }

        private String name;
        private String email;
    }
}
