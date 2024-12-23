package com.hyeyeoung.study.domain.common.entity;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class BaseEntity {

    @Column
    private Long createdBy;

    @Column
    private LocalDateTime createdDate;

    @Column
    private Long modifiedBy;

    @Column
    private LocalDateTime modifiedDate;
}
