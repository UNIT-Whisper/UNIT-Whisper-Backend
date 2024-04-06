package com.unit.whisper.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccessTokenDetail {

    private final Long userId;
    private final String role;
}
