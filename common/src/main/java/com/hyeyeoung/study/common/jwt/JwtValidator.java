package com.hyeyeoung.study.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtValidator {

    private final JwtProperties jwtProperties;

    // 토큰 유효성 검증
    public boolean validate(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false; // 유효하지 않은 토큰
        }
    }

    // 유효한 토큰에서 사용자 ID 추출
    public String getUserIdFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
