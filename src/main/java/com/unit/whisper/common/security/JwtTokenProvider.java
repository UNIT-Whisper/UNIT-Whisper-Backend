package com.unit.whisper.common.security;

import static com.unit.whisper.util.StaticValue.*;

import com.unit.whisper.dto.AccessTokenDetail;
import com.unit.whisper.exception.ExpiredRefreshTokenException;
import com.unit.whisper.exception.ExpiredTokenException;
import com.unit.whisper.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private Jws<Claims> getJws(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    private String buildAccessToken(
            Long id, Date issuedAt, Date accessTokenExpiresIn, String role) {
        final Key encodedKey = getSecretKey();
        return Jwts.builder()
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(issuedAt)
                .setSubject(id.toString())
                .claim(TOKEN_TYPE, ACCESS_TOKEN)
                .claim("role", role)
                .setExpiration(accessTokenExpiresIn)
                .signWith(encodedKey)
                .compact();
    }

    private String buildRefreshToken(Long id, Date issuedAt, Date refreshTokenExpiresIn) {
        final Key encodedKey = getSecretKey();
        return Jwts.builder()
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(issuedAt)
                .setSubject(id.toString())
                .claim(TOKEN_TYPE, REFRESH_TOKEN)
                .setExpiration(refreshTokenExpiresIn)
                .signWith(encodedKey)
                .compact();
    }

    public String generateAccessToken(Long id, String role) {
        final Date issuedAt = new Date();
        final Date accessTokenExpiresIn =
                new Date(issuedAt.getTime() + jwtProperties.getAccessExp() * 1000);

        return buildAccessToken(id, issuedAt, accessTokenExpiresIn, role);
    }

    public String generateRefreshToken(Long id) {
        final Date issuedAt = new Date();
        final Date refreshTokenExpiresIn =
                new Date(issuedAt.getTime() + jwtProperties.getRefreshExp() * 1000);
        return buildRefreshToken(id, issuedAt, refreshTokenExpiresIn);
    }

    public boolean isAccessToken(String token) {
        return getJws(token).getBody().get(TOKEN_TYPE).equals(ACCESS_TOKEN);
    }

    public boolean isRefreshToken(String token) {
        return getJws(token).getBody().get(TOKEN_TYPE).equals(REFRESH_TOKEN);
    }

    public AccessTokenDetail parseAccessToken(String token) {
        if (isAccessToken(token)) {
            Claims claims = getJws(token).getBody();
            return AccessTokenDetail.builder()
                    .userId(Long.parseLong(claims.getSubject()))
                    .role((String) claims.get("role"))
                    .build();
        }
        throw new InvalidTokenException();
    }

    public Long parseRefreshToken(String token) {
        try {
            if (isRefreshToken(token)) {
                Claims claims = getJws(token).getBody();
                return Long.parseLong(claims.getSubject());
            }
        } catch (ExpiredRefreshTokenException e) {
            throw new ExpiredRefreshTokenException();
        }
        throw new InvalidTokenException();
    }

    public Long getRefreshTokenTTlSecond() {
        return jwtProperties.getRefreshExp();
    }

    public Long getAccessTokenTTlSecond() {
        return jwtProperties.getAccessExp();
    }

    public Long getLeftAccessTokenTTLSecond(String token) {
        return getJws(token).getBody().getExpiration().getTime();
    }
}
