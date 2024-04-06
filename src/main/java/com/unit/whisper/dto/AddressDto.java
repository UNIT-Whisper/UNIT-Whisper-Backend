package com.unit.whisper.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public record AddressDto(
        String addressName,
        String region1depthName,
        String region2depthName,
        String region3depthName,
        String region3depthHName,
        String mainAddressNo,
        String subAddressNo) {}
