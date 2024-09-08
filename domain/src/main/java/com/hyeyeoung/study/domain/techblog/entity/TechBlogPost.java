package com.hyeyeoung.study.domain.techblog.entity;


import com.hyeyeoung.study.common.constant.TableConstants;
import com.hyeyeoung.study.domain.techblog.enums.TechBlogEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = TableConstants.TECH_BLOG_POST)
public class TechBlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long techBlogPostSeq;

    @Column
    @Enumerated(EnumType.STRING)
    private TechBlogEnum techBlogEnum;

    @Column
    private String title;

    @Column
    private String url;

    @Column
    private Long createdBy;

    @Column
    private LocalDateTime createdDate;

    @Column
    private Long modifiedBy;

    @Column
    private LocalDateTime modifiedDate;

    public static TechBlogPost of(TechBlogEnum techBlogEnum, String title, String url) {
        return TechBlogPost.builder()
                .techBlogEnum(techBlogEnum)
                .title(title)
                .url(url)
                .createdBy(1L)
                .createdDate(LocalDateTime.now())
                .modifiedBy(1L)
                .modifiedDate(LocalDateTime.now())
                .build();
    }
}
