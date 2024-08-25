package com.hyeyeoung.study.response.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiResponseEnum implements ApiResponseEnumInterface {

    SUCCESS(0, "Success", true),

    /**
     * 범용 오류 코드
     */
    NOT_EXIST(1001, "Entity is not exist", false),
    INTERNAL_SERVER_ERROR(1500, "Internal Server Error", false),

    ;

    private final Integer code;
    private final String message;
    private final boolean isSuccessful;

}
