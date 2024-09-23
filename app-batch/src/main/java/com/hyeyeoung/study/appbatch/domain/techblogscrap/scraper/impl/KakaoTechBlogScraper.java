package com.hyeyeoung.study.appbatch.domain.techblogscrap.scraper.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.json.KakaoJsonData;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.scraper.TechBlogScraper;
import com.hyeyeoung.study.domain.techblog.entity.TechBlogPost;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoTechBlogScraper implements TechBlogScraper {

    private static final String JSON_ELEMENT_ID = "__NUXT_DATA__";

    @Override
    public List<TechBlogPost> scrap(TechBlogScrapEnum techBlogScrapEnum) {
        try {
            String jsonData = extractJsonData(techBlogScrapEnum.getBlogUrl());
            return parseTechBlogPosts(techBlogScrapEnum, jsonData);
        } catch (IOException e) {
            // TODO: 오류를 보완하자
            throw new RuntimeException("Failed to extract JSON data from blog URL: " + techBlogScrapEnum.getBlogUrl(), e);
        }
    }

    private String extractJsonData(String blogUrl) throws IOException {
        Document document = Jsoup.connect(blogUrl).get();

        // <script> 태그를 선택합니다
        Element scriptElement = document.getElementById(JSON_ELEMENT_ID);

        // JSON 데이터를 추출합니다
        return requireNonNull(scriptElement, "No script element found with ID: " + JSON_ELEMENT_ID).html();
    }

    private List<TechBlogPost> parseTechBlogPosts(TechBlogScrapEnum techBlogScrapEnum, String jsonData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(jsonData);

        List<TechBlogPost> techBlogPosts = new ArrayList<>();

        rootNode.forEach(jsonNode -> {
            Optional<KakaoJsonData> optionalKakaoJsonData = parseTechBlogPost(jsonNode, objectMapper);
            optionalKakaoJsonData.ifPresent(kakaoJsonData -> techBlogPosts.add(kakaoJsonData.toTechBlogPost(techBlogScrapEnum, rootNode)));
        });

        return techBlogPosts;
    }

    private Optional<KakaoJsonData> parseTechBlogPost(JsonNode node, ObjectMapper objectMapper) {
        if (!node.isObject()) return Optional.empty();

        // 노드가 Object일 때만 변환 시도
        try {
            return Optional.of(objectMapper.treeToValue(node, KakaoJsonData.class));
        } catch (IllegalArgumentException | JsonProcessingException e) {
            // 변환 불가능한 경우 예외 처리
            return Optional.empty();
        }
    }

}
