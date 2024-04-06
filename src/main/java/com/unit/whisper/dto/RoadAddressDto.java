package com.unit.whisper.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public record RoadAddressDto(
        String addressName,
        String region1depthName,
        String region2depthName,
        String region3depthName,
        String roadName,
        String mainBuildingNo,
        String subBuildingNo,
        String buildingName,
        String zoneNo) {}
