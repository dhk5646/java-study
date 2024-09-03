package com.hyeyeoung.study.common.response.exception;

import com.hyeyeoung.study.common.response.ApiResponse;
import com.hyeyeoung.study.common.response.enums.ApiResponseEnumInterface;
import lombok.Getter;

@Getter
public class ApiResponseException extends RuntimeException {
    private final ApiResponse<Object> apiResponse;

    public ApiResponseException(ApiResponseEnumInterface apiResponseEnum) {
        super(apiResponseEnum.getMessage());
        this.apiResponse = ApiResponse.success(apiResponseEnum);
    }

    public <T> ApiResponseException(ApiResponseEnumInterface apiResponseEnum, T data) {
        super(apiResponseEnum.getMessage());
        this.apiResponse = ApiResponse.success(apiResponseEnum, data);
    }

}
