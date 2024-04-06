package com.unit.whisper.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record KakaoMapApiResponse(@JsonProperty(value = "documents") List<Document> documents) {}
