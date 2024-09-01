package com.hyeyeoung.study.response.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiResponseEnum implements ApiResponseEnumInterface {

    /**
     * http 코드
     */
    OK(200, "Success"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    /**
     * 범용 오류 코드
     */
    NOT_EXIST(1001, "Entity is not exist"),


    ;

    private final Integer code;
    private final String message;

}
