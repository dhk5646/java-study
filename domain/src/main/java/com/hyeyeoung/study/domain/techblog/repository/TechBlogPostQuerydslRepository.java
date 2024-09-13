package com.hyeyeoung.study.domain.techblog.repository;

import com.hyeyeoung.study.domain.techblog.dto.query.TechBlogPostQuery;
import com.hyeyeoung.study.domain.techblog.entity.TechBlogPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hyeyeoung.study.domain.common.util.QuerydslUtils.eq;
import static com.hyeyeoung.study.domain.techblog.entity.QTechBlogPost.techBlogPost;

@RequiredArgsConstructor
@Repository
public class TechBlogPostQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<TechBlogPost> findTechBlogPosts(TechBlogPostQuery query) {
        return jpaQueryFactory.selectFrom(techBlogPost)
                .where(eq(techBlogPost.techBlogEnum, query.getTechBlogEnum()),
                        eq(techBlogPost.title, query.getTitle()))
                .orderBy(techBlogPost.publishedDateTime.desc())
                .fetch();
    }

}
