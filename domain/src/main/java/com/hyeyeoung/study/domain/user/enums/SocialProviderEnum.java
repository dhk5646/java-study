package com.hyeyeoung.study.domain.user.enums;

import com.hyeyeoung.study.domain.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialProviderEnum implements BaseEnum<SocialProviderEnum> {

    GOOGLE("구글", 1),
    FACEBOOK("페이스북", 2),
    KAKAO("카카오", 3),
    NAVER("네이버", 4),
    ;

    private final String name;
    private final Integer sort;

}
