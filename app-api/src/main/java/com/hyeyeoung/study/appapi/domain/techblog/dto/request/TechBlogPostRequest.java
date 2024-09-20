package com.hyeyeoung.study.appapi.domain.techblog.dto.request;

import com.hyeyeoung.study.appapi.common.dto.request.PageRequest;
import com.hyeyeoung.study.domain.techblog.dto.criteria.TechBlogPostCriteria;
import com.hyeyeoung.study.domain.techblog.enums.TechBlogEnum;
import lombok.Getter;

@Getter
public class TechBlogPostRequest extends PageRequest {

    private final TechBlogEnum techBlogEnum;
    private final String title;

    protected TechBlogPostRequest(Integer pageNumber,
                                  Integer pageSize,
                                  TechBlogEnum techBlogEnum,
                                  String title) {
        super(pageNumber, pageSize);
        this.techBlogEnum = techBlogEnum;
        this.title = title;
    }

    public TechBlogPostCriteria toTechBlogPostCriteria() {
        return TechBlogPostCriteria.of(this.techBlogEnum, this.title);
    }
}
