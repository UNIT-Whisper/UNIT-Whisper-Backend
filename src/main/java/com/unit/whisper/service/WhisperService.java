package com.unit.whisper.service;


import com.unit.whisper.domain.user.User;
import com.unit.whisper.domain.whisper.Address;
import com.unit.whisper.domain.whisper.Whisper;
import com.unit.whisper.dto.Document;
import com.unit.whisper.dto.KakaoMapApiResponse;
import com.unit.whisper.dto.WhisperCreateRequest;
import com.unit.whisper.error.exception.KakaoMapException;
import com.unit.whisper.error.exception.UserNotFoundException;
import com.unit.whisper.external.ExternalClientProperties;
import com.unit.whisper.repository.UserRepository;
import com.unit.whisper.repository.WhisperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class WhisperService {

    private final WhisperRepository whisperRepository;
    private final UserRepository userRepository;
    ExternalClientProperties properties;

    public void saveWhisper(final Long memberId, final WhisperCreateRequest request) {
        User user = userRepository.findById(memberId).orElseThrow(UserNotFoundException::new);

        KakaoMapApiResponse response =
            WebClient.builder()
                .baseUrl("https://dapi.kakao.com")
                .build()
                .get()
                .uri(
                    uriBuilder ->
                        uriBuilder
                            .path("/v2/local/geo/coord2address.json")
                            .queryParam("x", String.valueOf(request.longitude()))
                            .queryParam("y", String.valueOf(request.longitude()))
                            .build())
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "KakaoAK " + "5818289447397369d006bcd7145c25d6")
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
                        .user(user)
                        .address(address)
                        .build();

        whisperRepository.save(whisper);
    }
}
