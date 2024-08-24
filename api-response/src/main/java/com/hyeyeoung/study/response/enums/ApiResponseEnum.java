package com.hyeyeoung.study.response.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiResponseEnum implements ApiResponseEnumInterface {
    
    SUCCESS(0, "Success", true),

    NOT_EXIST(1001, "Entity is not exist", false),
    ;

    private final Integer code;
    private final String message;
    private final boolean isSuccessful;

}
