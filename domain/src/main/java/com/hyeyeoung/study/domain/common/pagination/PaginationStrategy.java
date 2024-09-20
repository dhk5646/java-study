package com.hyeyeoung.study.domain.common.pagination;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaginationStrategy<T> {

    Page<T> paginate(JPAQuery<T> contentQuery, JPAQuery<Long> countQuery, Pageable pageable);

}