package com.hyeyeoung.study.appbatch.domain.techblogscrap.json;

import com.hyeyeoung.study.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import com.hyeyeoung.study.domain.techblog.entity.TechBlogPost;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class NaverJsonData {
    private List<NaverContent> content;

    public List<TechBlogPost> toTechBlogPosts() {
        return content.stream().map(NaverContent::toTechBlogPost)
                .collect(Collectors.toList());
    }

    @Getter
    @Setter
    public static class NaverContent {
        private String author;
        private String postHtml;
        private String postImage;
        private Long postPublishedAt;
        private String postTitle;
        private String url;

        private TechBlogPost toTechBlogPost() {
            return TechBlogPost.of(TechBlogScrapEnum.NAVER.getTechBlogEnum(),
                    this.postTitle,
                    TechBlogScrapEnum.NAVER.getPostUrl(this.url),
                    toPublishedDateTime());
        }

        private LocalDateTime toPublishedDateTime() {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(this.postPublishedAt), ZoneId.systemDefault());
        }
    }

}
