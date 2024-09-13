package com.hyeyeoung.study.domain.common.util;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.StringPath;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

@UtilityClass
public class QuerydslUtils {

    public static BooleanExpression eq(StringPath path, String right) {
        if (StringUtils.hasText(right)) return path.eq(right);
        return null;
    }

    public static <T extends Enum<T>> BooleanExpression eq(EnumPath<T> path, T right) {
        if (right == null) return null;
        return path.eq(right);
    }
}
