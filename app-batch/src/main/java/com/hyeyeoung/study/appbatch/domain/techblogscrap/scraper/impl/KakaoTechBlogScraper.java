package com.hyeyeoung.study.appbatch.domain.techblogscrap.scraper.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.scraper.TechBlogScraper;
import com.hyeyeoung.study.domain.techblog.entity.TechBlogPost;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Component
@RequiredArgsConstructor
public class KakaoTechBlogScraper implements TechBlogScraper {

    private static final String JSON_ELEMENT_ID = "__NUXT_DATA__";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String RELEASE_DATE_TIME = "releaseDateTime";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    @Override
    public List<TechBlogPost> scrap(TechBlogScrapEnum techBlogScrapEnum) {

        try {
            Document document = Jsoup.connect(techBlogScrapEnum.getBlogUrl()).get();

            // <script> 태그를 선택합니다
            Element scriptElement = document.getElementById(JSON_ELEMENT_ID);

            // JSON 데이터를 추출합니다
            String jsonData = requireNonNull(scriptElement).html();

            // JSON 데이터를 파싱합니다
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonData);

            List<TechBlogPost> techBlogPosts = new ArrayList<>();
            for (JsonNode postIndex : rootNode.get(6)) { // 포스트 정보가 포함된 배열의 위치

                JsonNode post = rootNode.get(postIndex.asInt());

                String postNumber = rootNode.get(post.get(ID).asInt()).asText(); // post number

                String postUrl = techBlogScrapEnum.getPostUrl(postNumber);

                String title = rootNode.get(post.get(TITLE).asInt()).asText();

                LocalDateTime publishedDateTime = this.parseDateTime(rootNode.get(post.get(RELEASE_DATE_TIME).asInt()).asText());

                TechBlogPost techBlogPost = TechBlogPost.of(techBlogScrapEnum.getTechBlogEnum(), title, postUrl, publishedDateTime);

                techBlogPosts.add(techBlogPost);

            }

            return techBlogPosts;

        } catch (IOException e) {
            // TODO: 오류를 보완하자
            throw new RuntimeException(e);
        }
    }

    /**
     * @param dateTimeString yyyy.MM.dd HH:mm:ss
     */
    private LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, formatter);
    }

}
