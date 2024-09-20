package com.hyeyeoung.study.domain.common.pagination;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PaginationHelper {

    public static <T> Page<T> paginate(JPAQuery<T> contentQuery,
                                       JPAQuery<Long> countQuery,
                                       Pageable pageable,
                                       PaginationStrategy<T> strategy) {

        return strategy.paginate(contentQuery, countQuery, pageable);
    }

    public static <T> Page<T> paginate(JPAQuery<T> contentQuery,
                                       JPAQuery<Long> countQuery,
                                       Pageable pageable) {
        return paginate(contentQuery, countQuery, pageable, new DefaultPaginationStrategy<>());
    }
}
