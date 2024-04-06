package com.unit.whisper.service;

import static com.unit.whisper.util.StaticValue.BEARER;

import com.unit.whisper.common.security.JwtTokenProvider;
import com.unit.whisper.domain.user.User;
import com.unit.whisper.domain.user.adaptor.UserAdaptor;
import com.unit.whisper.entrypoint.request.AuthRequest;
import com.unit.whisper.entrypoint.response.AuthResponse;
import com.unit.whisper.enumeration.ResultCode;
import com.unit.whisper.exception.BaseException;
import com.unit.whisper.external.kakao.KakaoRestful;
import com.unit.whisper.external.kakao.dto.request.KakaoAuthRequest;
import com.unit.whisper.external.kakao.dto.response.KakaoAuthPayload;
import com.unit.whisper.external.kakao.dto.response.KakaoUserInfoPayload;
import com.unit.whisper.repository.UserRepository;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final KakaoRestful kakaoRestful;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserAdaptor userAdaptor;
    private final RedisTemplate<String, String> redisTemplate;

    @Value("${client.kakao.authBaseUrl}")
    private String kakaoAuthUrl;

    @Value("${client.kakao.apiBaseUrl}")
    private String kakaoApiUrl;

    @Value("${client.kakao.clientId}")
    private String clientId;

    @Value("${client.kakao.clientSecret}")
    private String clientSecret;

    @Transactional
    public AuthResponse loginKakao(AuthRequest request) {
        KakaoUserInfoPayload kakaoUserInfoResponse =
                getKakaoUserInfo(
                        getKakaoAuthToken(request.getAuthCode(), request.getRedirectUri()));

        return saveUserAndGetToken(kakaoUserInfoResponse);
    }

    public AuthResponse saveUserAndGetToken(KakaoUserInfoPayload kakaoUserInfoPayload) {
        checkLoginEmail(kakaoUserInfoPayload.getKakaoAccount().getEmail());
        User user =
                userRepository
                        .findByEmail(kakaoUserInfoPayload.getKakaoAccount().getEmail())
                        .orElseGet(() -> createUser(kakaoUserInfoPayload));

        return createAuthResponse(user);
    }

    public AuthResponse reissue(String refreshToken) {
        Long userId = jwtTokenProvider.parseRefreshToken(refreshToken);
        return createAuthResponse(userAdaptor.findById(userId));
    }

    public void logout(String token) {
        String parseToken = validateAndParseToken(token);
        Long leftAccessTokenTTlSecond = jwtTokenProvider.getLeftAccessTokenTTLSecond(parseToken);

        redisTemplate
                .opsForValue()
                .set(parseToken, "logout", leftAccessTokenTTlSecond, TimeUnit.MILLISECONDS);
    }

    private void checkLoginEmail(String email) {
        if (email == null) {
            throw new BaseException(ResultCode.FAIL);
        }
    }

    private User createUser(KakaoUserInfoPayload kakaoUserInfoPayload) {
        User user =
                User.toEntity(
                        kakaoUserInfoPayload.getKakaoAccount().getEmail(),
                        kakaoUserInfoPayload.getKakaoAccount().getProfile().getNickname());

        return userRepository.save(user);
    }

    private AuthResponse createAuthResponse(User user) {
        return AuthResponse.of(
                user.getId(),
                jwtTokenProvider.generateAccessToken(user.getId(), "USER"),
                jwtTokenProvider.generateRefreshToken(user.getId()),
                jwtTokenProvider.getAccessTokenTTlSecond());
    }

    private KakaoAuthPayload getKakaoAuthToken(String authCode, String redirectUri) {
        return kakaoRestful.getKakaoAuthInfo(
                KakaoAuthRequest.createKakaoAuthRequest(
                        clientId, redirectUri, authCode, clientSecret));
    }

    private KakaoUserInfoPayload getKakaoUserInfo(KakaoAuthPayload response) {
        return kakaoRestful.getKakaoUserInfo(response.getAccessToken());
    }

    private String validateAndParseToken(String token) {
        return (token != null && token.startsWith(BEARER))
                ? token.substring(BEARER.length())
                : null;
    }
}
