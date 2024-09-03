package com.hyeyeoung.study.common.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    // 헬스 상태를 반환하는 메서드
    @Override
    public Health health() {
        // 특정 로직을 통해 오류 상태를 확인
        int errorCode = check();

        // 오류가 발생한 경우 'down' 상태와 세부 정보를 리턴
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode).build();
        }

        // 정상 상태일 경우 'up' 상태 리턴
        return Health.up().build();
    }

    // 헬스 상태를 확인하는 메서드 (사용자 정의 로직 구현)
    private int check() {
        // perform some specific health check
        // 예시: 외부 서비스 호출, 파일 시스템 체크 등
        return 0;  // 정상일 경우 0, 오류가 있을 경우 다른 값 반환
    }
}