package com.hyeyeoung.study.domain.common.enums;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public interface BaseEnum<T extends Enum<T> & BaseEnum<T>> { // BaseEnum 인터페이스를 구현하는 클래스가 enum이어야 하며, 동시에 BaseEnum 인터페이스를 구현해야 한다

    String getName();

    Integer getSort();

    default Optional<T> findByName(String name) {
        if (StringUtils.hasText(name)) return Optional.empty();

        T[] enumConstants = ((Class<T>) this.getClass()).getEnumConstants();

        return Arrays.stream(enumConstants) // Enum의 값을 가져옵니다
                .filter(item -> item.getName().equals(name))  // 정확히 일치하는 이름 찾기
                .findFirst();
    }
}
