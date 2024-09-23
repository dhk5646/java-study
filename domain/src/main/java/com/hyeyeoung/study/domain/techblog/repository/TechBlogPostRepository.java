package com.hyeyeoung.study.domain.techblog.repository;

import com.hyeyeoung.study.domain.techblog.entity.TechBlogPost;
import com.hyeyeoung.study.domain.techblog.enums.TechBlogEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechBlogPostRepository extends JpaRepository<TechBlogPost, Long>, TechBlogPostQueryRepository {

    List<TechBlogPost> findByTechBlogEnum(TechBlogEnum techBlogScrapEnum);
}
