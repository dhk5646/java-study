package com.hyeyeoung.study.domain.techblog.dto.criteria;

import com.hyeyeoung.study.domain.techblog.enums.TechBlogEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TechBlogPostCriteria {

    private final TechBlogEnum techBlogEnum;
    private final String title;

    public static TechBlogPostCriteria of(TechBlogEnum techBlogEnum, String title) {
        return new TechBlogPostCriteria(techBlogEnum, title);
    }
}
