package com.hyeyeoung.study.appapi.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Set;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final Set<String> excludedUrls = Set.of(
            "/user/login"
    );

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (isExcludedUrl(request)) return true;

        String authToken = request.getHeader("Authorization");

        // 토큰이 없거나 잘못된 경우
        if (!isValidToken(authToken)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            return false;
        }

        // 인증 성공
        return true;
    }

    private boolean isExcludedUrl(HttpServletRequest request) {
        String requestUri = request.getRequestURI();

        // 요청 경로가 excludedUrls에 매칭되는지 확인
        for (String pattern : excludedUrls) {
            if (pathMatcher.match(pattern, requestUri)) {
                return true; // 인증 제외 경로, 요청을 그대로 진행
            }
        }
        return false;
    }

    private boolean isValidToken(String token) {
        // 실제 인증 로직 (예: 토큰 검증)
        return "VALID_TOKEN".equals(token); // 예시로 단순 비교
    }
}
