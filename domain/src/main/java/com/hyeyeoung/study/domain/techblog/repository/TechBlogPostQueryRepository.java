package com.hyeyeoung.study.domain.techblog.repository;

import com.hyeyeoung.study.domain.techblog.dto.criteria.TechBlogPostCriteria;
import com.hyeyeoung.study.domain.techblog.dto.result.TechBlogPostResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TechBlogPostQueryRepository {

    Page<TechBlogPostResult> searchTechBlogPosts(TechBlogPostCriteria criteria, Pageable pageable);

}
