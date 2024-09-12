package com.hyeyeoung.study.appapi.domain.sample.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Samples")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sampleSeq;

    @Column
    private String content;

    @Column
    private Long createdBy;

    @Column
    private LocalDateTime createdDate;

    @Column
    private Long modifiedBy;

    @Column
    private LocalDateTime modifiedDate;

    public static Sample of(String content) {
        return new Sample(
                null,
                content,
                1L,
                LocalDateTime.now(),
                1L,
                LocalDateTime.now()
        );
    }

    public static Sample empty() {
        return new Sample(
                null,
                null,
                1L,
                LocalDateTime.now(),
                1L,
                LocalDateTime.now()
        );
    }

    public void modifyContent(String content) {
        this.content = content;
    }
}
