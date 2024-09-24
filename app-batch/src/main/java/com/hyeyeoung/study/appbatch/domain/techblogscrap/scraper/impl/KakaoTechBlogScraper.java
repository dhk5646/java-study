package com.hyeyeoung.study.appbatch.domain.techblogscrap.scraper.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.json.KakaoJsonData;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.scraper.TechBlogScraper;
import com.hyeyeoung.study.domain.techblog.entity.TechBlogPost;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoTechBlogScraper implements TechBlogScraper {

    private final ObjectMapper objectMapper;

    @Override
    public List<TechBlogPost> scrap(TechBlogScrapEnum techBlogScrapEnum) {
        try {
            String jsonData = extractJsonData(techBlogScrapEnum.getBlogUrl());
            return parseTechBlogPosts(jsonData);
        } catch (IOException e) {
            // TODO: 오류를 보완하자
            throw new RuntimeException("Failed to extract JSON data from blog URL: " + techBlogScrapEnum.getBlogUrl(), e);
        }
    }

    private String extractJsonData(String blogUrl) throws IOException {
        return WebClient.builder()
                .baseUrl(blogUrl) // 기본 베이스 URL 설정
                .build()
                .get() // GET 요청 설정
                .retrieve() // HTTP 응답을 받아옴
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(), // 4xx 또는 5xx 에러 처리
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    // TODO: 에러처리 보완
                                    // 에러 메시지를 포함하여 예외를 발생
                                    return Mono.error(new RuntimeException("Kakao API error: " + errorBody));
                                })
                )
                .bodyToMono(String.class)
                .block(); // 응답 바디를 String 타입으로 변환
    }

    private List<TechBlogPost> parseTechBlogPosts(String jsonData) throws JsonProcessingException {
        KakaoJsonData kakaoJsonData = objectMapper.readValue(jsonData, KakaoJsonData.class);
        return kakaoJsonData.toTechBlogPosts();
    }
}
