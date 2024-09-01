package com.hyeyeoung.study.response.exception;

import com.hyeyeoung.study.response.ApiResponse;
import com.hyeyeoung.study.response.enums.ApiResponseEnumInterface;
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
