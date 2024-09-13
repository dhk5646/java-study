package com.hyeyeoung.study.domain.techblog.dto.query;

import com.hyeyeoung.study.domain.techblog.enums.TechBlogEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TechBlogPostQuery {

    private final TechBlogEnum techBlogEnum;
    private final String title;

}
