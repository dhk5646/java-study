package com.hyeyeoung.study.appapi.common.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
public class PageRequest {

    @NotNull
    @Min(0)
    private final Integer pageNumber;

    @NotNull
    @Min(1)
    private final Integer pageSize;

    protected PageRequest(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Pageable toPageable() {
        return org.springframework.data.domain.PageRequest.of(this.pageNumber, this.pageSize, Sort.unsorted());
    }
}
