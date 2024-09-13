package com.hyeyeoung.study.domain.techblog.repository;

import com.hyeyeoung.study.domain.techblog.entity.TechBlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechBlogPostJpaRepository extends JpaRepository<TechBlogPost, Long> {

    boolean existsByUrl(String postUrl);

}
