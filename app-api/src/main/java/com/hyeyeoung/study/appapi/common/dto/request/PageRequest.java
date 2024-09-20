package com.hyeyeoung.study.appapi.common.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
public class PageRequest {

    @NotNull
    private final Integer pageNumber; // 현재 페이지 번호

    @NotNull
    @Min(1)
    private final Integer pageSize; // 페이지 크기

    protected PageRequest(Integer pageNumber, Integer pageSize) {
        if (pageNumber < 1) {
            throw new IllegalArgumentException("Page number must be greater than or equal to 1.");
        }
        this.pageNumber = pageNumber - 1; // 내부적으로 0부터 시작
        this.pageSize = pageSize;
    }

    public Pageable toPageable() {
        return org.springframework.data.domain.PageRequest.of(this.pageNumber, this.pageSize, Sort.unsorted());
    }
}
