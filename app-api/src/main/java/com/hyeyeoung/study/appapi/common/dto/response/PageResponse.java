package com.hyeyeoung.study.appapi.common.dto.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageResponse<T> {
    private final List<T> content;  // 실제 데이터 목록
    private final PageData<T> pageData;

    // 생성자
    public PageResponse(Page<T> page) {
        this.content = page.getContent();
        this.pageData = new PageData<>(page);
    }

    @Getter
    private static class PageData<T> {
        private final int pageNumber;   // 현재 페이지 번호
        private final int pageSize;     // 페이지 크기
        private final long totalElements; // 전체 레코드 수
        private final int totalPages;   // 전체 페이지 수
        private final boolean isLast;   // 마지막 페이지 여부

        public PageData(Page<T> page) {
            this.pageNumber = page.getNumber() + 1;
            this.pageSize = page.getSize();
            this.totalElements = page.getTotalElements();
            this.totalPages = page.getTotalPages();
            this.isLast = page.isLast();
        }
    }

    public static <T> PageResponse<T> of(Page<T> page) {
        return new PageResponse<>(page);
    }
}
