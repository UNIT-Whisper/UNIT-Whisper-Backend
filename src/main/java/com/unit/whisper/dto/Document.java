package com.unit.whisper.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.unit.whisper.domain.whisper.Address;

public record Document(
        @JsonProperty(value = "road_address") RoadAddressDto roadAddressDto,
        @JsonProperty(value = "address") AddressDto addressDto) {

    public Address toAddress() {
        return Address.builder()
                .fullRoadAddressName(roadAddressDto.addressName())
                .province(roadAddressDto.region1depthName())
                .city(roadAddressDto.region2depthName())
                .subDistinct(roadAddressDto.region3depthName())
                .roadName(roadAddressDto.roadName())
                .mainBuildingNumber(roadAddressDto.mainBuildingNo())
                .subBuildingNumber(roadAddressDto.subBuildingNo())
                .buildingName(roadAddressDto.buildingName())
                .zipCode(roadAddressDto.zoneNo())
                .build();
    }
}
