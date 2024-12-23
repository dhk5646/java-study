package com.hyeyeoung.study.common.response.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiResponseEnum implements ApiResponseEnumInterface {

    /**
     * http 코드
     */
    OK(200, "Success"),
    UNAUTHORIZED(401, "Unauthorized"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),


    /**
     * 범용 오류 코드
     */
    NOT_EXIST(1001, "Entity is not exist"),

    /**
     * 유저
     */
    USER_IN_ACTIVE(2001, "User is not in active"),
    USER_LOCKED(2002, "User is lock"),
    ;

    private final Integer code;
    private final String message;

}
