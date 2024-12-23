package com.hyeyeoung.study.domain.user.enums;

import com.hyeyeoung.study.domain.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusEnum implements BaseEnum<UserStatusEnum> {
    ACTIVE("활성화", 1),
    INACTIVE("비활성화", 2),
    LOCKED("잠김", 3),
    DELETED("삭제", 4),
    ;

    private final String name;
    private final Integer sort;

}
