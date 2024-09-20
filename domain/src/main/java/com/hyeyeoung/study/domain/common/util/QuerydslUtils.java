package com.hyeyeoung.study.domain.common.util;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.StringPath;
import lombok.experimental.UtilityClass;

import static org.springframework.util.StringUtils.hasText;

@UtilityClass
public class QuerydslUtils {

    public static BooleanExpression eq(StringPath path, String right) {
        return hasText(right) ? path.eq(right) : null;
    }

    public static <T extends Enum<T>> BooleanExpression eq(EnumPath<T> path, T right) {
        return right == null ? null : path.eq(right);
    }

    public static BooleanExpression contains(StringPath path, String str) {
        return hasText(str) ? path.contains(str) : null;
    }
}
