package com.hyeyeoung.study.appbatch.domain.techblogscrap.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import com.hyeyeoung.study.domain.techblog.entity.TechBlogPost;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class KakaoJsonData {
    private Integer id;
    private Integer title;
    private Integer releaseDate;
    private Integer releaseDateTime;
    private Integer categories;
    private Integer author;
    private Integer thumbnailUri;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    @JsonCreator
    public KakaoJsonData(
            @JsonProperty(value = "id", required = true) Integer id,
            @JsonProperty(value = "title", required = true) Integer title,
            @JsonProperty(value = "releaseDate", required = true) Integer releaseDate,
            @JsonProperty(value = "releaseDateTime", required = true) Integer releaseDateTime,
            @JsonProperty(value = "categories", required = true) Integer categories,
            @JsonProperty(value = "author", required = true) Integer author,
            @JsonProperty(value = "thumbnailUri", required = true) Integer thumbnailUri) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.releaseDateTime = releaseDateTime;
        this.categories = categories;
        this.author = author;
        this.thumbnailUri = thumbnailUri;
    }

    public TechBlogPost toTechBlogPost(TechBlogScrapEnum techBlogScrapEnum, JsonNode rootNode) {
        String postNumber = rootNode.get(this.id).asText(); // post number
        String postUrl = techBlogScrapEnum.getPostUrl(postNumber);
        String title = rootNode.get(this.title).asText();
        LocalDateTime publishedDateTime = this.parseDateTime(rootNode.get(this.releaseDateTime).asText());

        return TechBlogPost.of(techBlogScrapEnum.getTechBlogEnum(), title, postUrl, publishedDateTime);
    }

    /**
     * @param dateTimeString yyyy.MM.dd HH:mm:ss
     */
    private LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, formatter);
    }
}
