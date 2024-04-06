package com.unit.whisper.external.kakao;


import com.unit.whisper.external.kakao.dto.response.KakaoAuthPayload;
import com.unit.whisper.external.kakao.dto.response.KakaoUserInfoPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoRestful {

    private final WebClient kakaoAuthApiWebClient;
    private final WebClient kakaoApiWebClient;

    private static final String GET_TOKEN_PATH = "/oauth/token";
    private static final String GET_USER_INFO_PATH = "/v2/user/me";

    /** 카카오 인증 토큰 획득 */
    public KakaoAuthPayload getKakaoAuthInfo(MultiValueMap<String, String> authRequest) {
        return kakaoAuthApiWebClient
                .mutate()
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder.path(GET_TOKEN_PATH).build())
                .body(BodyInserters.fromValue(authRequest))
                .retrieve()
                .onStatus(
                        httpStatusCode ->
                                httpStatusCode.is4xxClientError()
                                        || httpStatusCode.is5xxServerError(),
                        clientResponse -> Mono.error(new RuntimeException()))
                .bodyToMono(KakaoAuthPayload.class)
                .onErrorReturn(new KakaoAuthPayload())
                .block();
    }

    /** 카카오 로그인 정보 획득 */
    public KakaoUserInfoPayload getKakaoUserInfo(String accessToken) {
        log.info("accessToken : {}", accessToken);

        return kakaoApiWebClient
                .mutate()
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder.path(GET_USER_INFO_PATH).build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .onStatus(
                        httpStatusCode ->
                                httpStatusCode.is4xxClientError()
                                        || httpStatusCode.is5xxServerError(),
                        clientResponse -> Mono.error(new RuntimeException()))
                .bodyToMono(KakaoUserInfoPayload.class)
                .onErrorReturn(new KakaoUserInfoPayload())
                .block();
    }
}
