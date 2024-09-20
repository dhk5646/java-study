package com.hyeyeoung.study.domain.techblog.repository;

import com.hyeyeoung.study.domain.common.pagination.PaginationHelper;
import com.hyeyeoung.study.domain.techblog.dto.criteria.TechBlogPostCriteria;
import com.hyeyeoung.study.domain.techblog.dto.result.TechBlogPostResult;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.hyeyeoung.study.domain.common.util.QuerydslUtils.contains;
import static com.hyeyeoung.study.domain.common.util.QuerydslUtils.eq;
import static com.hyeyeoung.study.domain.techblog.entity.QTechBlogPost.techBlogPost;

@RequiredArgsConstructor
@Repository
public class TechBlogPostQueryRepositoryImpl implements TechBlogPostQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<TechBlogPostResult> searchTechBlogPosts(TechBlogPostCriteria criteria, Pageable pageable) {
        JPAQuery<TechBlogPostResult> contentQuery = this.techBlogPostQuery(criteria, Projections.constructor(TechBlogPostResult.class, techBlogPost));
        JPAQuery<Long> countQuery = this.techBlogPostQuery(criteria, techBlogPost.count());
        return PaginationHelper.paginate(contentQuery, countQuery, pageable);
    }

    private <T> JPAQuery<T> techBlogPostQuery(TechBlogPostCriteria criteria, Expression<T> expression) {
        return jpaQueryFactory.select(expression)
                .from(techBlogPost)
                .where(ExpressionUtils.or(eq(techBlogPost.techBlogEnum, criteria.getTechBlogEnum()), contains(techBlogPost.title, criteria.getQuery())))
                .orderBy(techBlogPost.publishedDateTime.desc());
    }


}
