package com.unit.whisper.service;


import com.unit.whisper.common.helper.UserHelper;
import com.unit.whisper.domain.user.User;
import com.unit.whisper.domain.whisper.Address;
import com.unit.whisper.domain.whisper.Whisper;
import com.unit.whisper.dto.KakaoMapApiResponse;
import com.unit.whisper.dto.WhisperCreateRequest;
import com.unit.whisper.error.exception.KakaoMapException;
import com.unit.whisper.external.ExternalClientProperties;
import com.unit.whisper.repository.WhisperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WhisperService {

    private final WhisperRepository whisperRepository;
    private final UserHelper userHelper;
    private final ExternalClientProperties properties;

    @Transactional
    public Long saveWhisper(WhisperCreateRequest request) {
        User currentUser = userHelper.getCurrentUser();

        KakaoMapApiResponse response =
                WebClient.builder()
                        .baseUrl("https://dapi.kakao.com")
                        .build()
                        .get()
                        .uri(
                                uriBuilder ->
                                        uriBuilder
                                                .path("/v2/local/geo/coord2address.json")
                                                .queryParam(
                                                        "x", String.valueOf(request.longitude()))
                                                .queryParam(
                                                        "y", String.valueOf(request.longitude()))
                                                .build())
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "KakaoAK " + properties.getKakao().getClientId())
                        .retrieve()
                        .bodyToMono(KakaoMapApiResponse.class)
                        .block();
        System.out.println(response);

        if (response == null || response.documents().isEmpty()) {
            throw new KakaoMapException();
        }

        Address address = response.documents().get(0).toAddress();

        Whisper whisper =
                Whisper.builder()
                        .content(request.content())
                        .latitude(request.latitude())
                        .longitude(request.longitude())
                        .user(currentUser)
                        .address(address)
                        .build();

        whisperRepository.save(whisper);
        currentUser.addWhisper(whisper);

        return whisper.getId();
    }
}
