package com.hyeyeoung.study.appbatch.domain.techblogscrap.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import com.hyeyeoung.study.domain.techblog.entity.TechBlogPost;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class KakaoJsonData {
    private List<KakaoPage> pages;

    public List<TechBlogPost> toTechBlogPosts() {
        return pages.stream()
                .flatMap(page -> page.getContents().stream())
                .map(KakaoPage.KakaoContent::toTechBlogPost)
                .collect(Collectors.toList());
    }


    @Getter
    @Setter
    private static class KakaoPage {
        private List<KakaoContent> contents;

        @Getter
        @Setter
        private static class KakaoContent {
            private int id;
            private String title;
            private String releaseDate;
            @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
            private LocalDateTime releaseDateTime; // LocalDateTime으로 변경
            private List<KakaoCategory> categories;
            private KakaoAuthor author;
            private String thumbnailUri;

            private TechBlogPost toTechBlogPost() {
                return TechBlogPost.of(TechBlogScrapEnum.KAKAO.getTechBlogEnum(),
                        this.title,
                        TechBlogScrapEnum.KAKAO.getPostUrl(String.valueOf(this.id)),
                        this.releaseDateTime);
            }

            @Getter
            @Setter
            private static class KakaoAuthor {
                private String name;
                private String description;
                private String profile;
            }

            @Getter
            @Setter
            private static class KakaoCategory {
                private String code;
                private String name;
            }


        }
    }
}
