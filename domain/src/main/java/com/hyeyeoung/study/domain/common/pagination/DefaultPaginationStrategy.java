package com.hyeyeoung.study.domain.common.pagination;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

public class DefaultPaginationStrategy<T> implements PaginationStrategy<T> {

    @Override
    public Page<T> paginate(JPAQuery<T> contentQuery, JPAQuery<Long> countQuery, Pageable pageable) {

        List<T> content = contentQuery.offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
        
    }
}