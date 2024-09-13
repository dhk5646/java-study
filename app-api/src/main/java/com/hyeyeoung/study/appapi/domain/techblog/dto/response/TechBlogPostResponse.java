package com.hyeyeoung.study.appapi.domain.techblog.dto.response;

import com.hyeyeoung.study.domain.techblog.dto.result.TechBlogPostResult;
import com.hyeyeoung.study.domain.techblog.enums.TechBlogEnum;

import java.time.LocalDateTime;

public record TechBlogPostResponse(Long techBlogPostSeq,
                                   TechBlogEnum techBlogEnum,
                                   String title,
                                   String url,
                                   LocalDateTime publishedDateTime) {

    public static TechBlogPostResponse create(TechBlogPostResult techBlogPost) {
        return new TechBlogPostResponse(
                techBlogPost.getTechBlogPostSeq(),
                techBlogPost.getTechBlogEnum(),
                techBlogPost.getTitle(),
                techBlogPost.getUrl(),
                techBlogPost.getPublishedDateTime()
        );
    }
}
