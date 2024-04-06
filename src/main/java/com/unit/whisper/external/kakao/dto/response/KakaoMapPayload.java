package com.unit.whisper.external.kakao.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;

@Getter
public class KakaoMapPayload {

    private Meta meta;

    @Getter
    public static class Meta {
        @JsonProperty("total_count")
        private int totalCount;

        @JsonProperty("pageable_count")
        private int pageableCount;

        @JsonProperty("is_end")
        private boolean isEnd;
    }

    private List<Document> documents;

    @Getter
    public static class Document {

        @JsonProperty("road_address")
        private RoadAddress roadAddress;

        @JsonProperty("address")
        private Address address;

        @Getter
        public static class RoadAddress {
            @JsonProperty("address_name")
            private String addressName;
        }

        @Getter
        public static class Address {
            @JsonProperty("address_name")
            private String addressName;
        }
    }
}
