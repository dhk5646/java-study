package com.hyeyeoung.study.common.jwt;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt") // jwt로 시작하는 설정값 주입
@Getter
@Setter
public class JwtProperties {
    private String secretKey;
    private long expirationTime;
}
