package com.example.vuestagram.util.jwt.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix ="config.jwt")
public class JwtConfig {
    private final String issuer; // 토큰을 발급하는 유저
    private final String type; // JWT
    private final int accessTokenExpiry; // 엑세스 토큰 유효 시간
    private final int refreshTokenExpiry; // 리프레시 토큰 유효시간
    private final String refreshTokenCookieName;
    private final int refreshTokenCookieExpiry; // 리프레시 토큰 쿠키 유효시간
    private final String secret; // 암호화 할때 사용하는 번호
    private final String headerKey;
    private final String schema;
    private final String reissUri;
}
