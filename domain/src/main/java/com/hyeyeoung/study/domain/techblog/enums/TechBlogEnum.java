package com.hyeyeoung.study.domain.techblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Getter
@AllArgsConstructor
public enum TechBlogEnum {

    KAKAO("카카오"),
    ;

    private final String name;

    public static Optional<TechBlogEnum> findByName(String name) {
        if (!hasText(name)) return Optional.empty();
        return Arrays.stream(values())
                .filter(item -> item.getName().contains(name))
                .findFirst();
    }
}
