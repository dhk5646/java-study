package com.hyeyeoung.study.appbatch.domain.techblogscrap.scraper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import com.hyeyeoung.study.domain.techblog.entity.TechBlogPost;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

public abstract class TechBlogScraper {

    protected TechBlogScrapEnum techBlogScrapEnum;
    protected ObjectMapper objectMapper;

    protected TechBlogScraper(TechBlogScrapEnum techBlogScrapEnum, ObjectMapper objectMapper) {
        this.techBlogScrapEnum = techBlogScrapEnum;
        this.objectMapper = objectMapper;
    }

    public abstract List<TechBlogPost> scrap();

    protected abstract List<TechBlogPost> parseTechBlogPosts(String jsonData) throws JsonProcessingException;

    protected String extractJsonData() throws IOException {
        return WebClient.builder()
                .baseUrl(techBlogScrapEnum.getBlogUrl()) // 기본 베이스 URL 설정
                .build()
                .get() // GET 요청 설정
                .retrieve() // HTTP 응답을 받아옴
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(), // 4xx 또는 5xx 에러 처리
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    // TODO: 에러처리 보완
                                    // 에러 메시지를 포함하여 예외를 발생
                                    return Mono.error(new RuntimeException("Naver API error: " + errorBody));
                                })
                )
                .bodyToMono(String.class)
                .block(); // 응답 바디를 String 타입으로 변환
    }
}
