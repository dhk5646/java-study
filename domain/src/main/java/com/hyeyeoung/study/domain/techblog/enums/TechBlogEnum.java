package com.hyeyeoung.study.domain.techblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TechBlogEnum {

    KAKAO("카카오"),
    ;

    private final String name;
}
