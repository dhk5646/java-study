package com.hyeyeoung.study.appapi.domain.techblog.dto.request;

import com.hyeyeoung.study.appapi.common.dto.request.PageRequest;
import com.hyeyeoung.study.domain.techblog.dto.criteria.TechBlogPostCriteria;
import lombok.Getter;

@Getter
public class TechBlogPostRequest extends PageRequest {

    private final String query;

    protected TechBlogPostRequest(Integer pageNumber,
                                  Integer pageSize,
                                  String query) {
        super(pageNumber, pageSize);
        this.query = query;
    }

    public TechBlogPostCriteria toTechBlogPostCriteria() {
        return TechBlogPostCriteria.of(this.query);
    }
}
