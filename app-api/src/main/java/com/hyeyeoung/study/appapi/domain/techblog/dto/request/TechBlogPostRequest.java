package com.hyeyeoung.study.appapi.domain.techblog.dto.request;

import com.hyeyeoung.study.domain.techblog.dto.query.TechBlogPostQuery;
import com.hyeyeoung.study.domain.techblog.enums.TechBlogEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TechBlogPostRequest {

    private TechBlogEnum techBlogEnum;
    private String title;

    public TechBlogPostQuery convetToTechBlogPostQuery() {
        return new TechBlogPostQuery(this.techBlogEnum, this.title);
    }
}
