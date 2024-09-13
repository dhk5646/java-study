package com.hyeyeoung.study.domain.techblog.dto.result;

import com.hyeyeoung.study.domain.techblog.entity.TechBlogPost;
import com.hyeyeoung.study.domain.techblog.enums.TechBlogEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TechBlogPostResult {

    private Long techBlogPostSeq;
    private TechBlogEnum techBlogEnum;
    private String title;
    private String url;
    private LocalDateTime publishedDateTime;

    public static TechBlogPostResult create(TechBlogPost techBlogPost) {
        return new TechBlogPostResult(
                techBlogPost.getTechBlogPostSeq(),
                techBlogPost.getTechBlogEnum(),
                techBlogPost.getTitle(),
                techBlogPost.getUrl(),
                techBlogPost.getPublishedDateTime()
        );
    }
}
