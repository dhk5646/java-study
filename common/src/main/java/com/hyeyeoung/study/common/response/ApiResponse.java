package com.hyeyeoung.study.common.response;

import com.hyeyeoung.study.common.response.enums.ApiResponseEnum;
import com.hyeyeoung.study.common.response.enums.ApiResponseEnumInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {
    private final Boolean isSuccessful;
    private final Integer code;
    private final String message;
    private final T data;

    public ApiResponse(boolean isSuccessful, ApiResponseEnumInterface apiResponseEnum, T data) {
        this.isSuccessful = isSuccessful;
        this.code = apiResponseEnum.getCode();
        this.message = apiResponseEnum.getMessage();
        this.data = data;
    }

    public static <T> ApiResponse<T> success(ApiResponseEnumInterface apiResponseEnum, T data) {
        return new ApiResponse<>(true, apiResponseEnum, data);
    }

    public static <T> ApiResponse<T> success() {
        return ApiResponse.success(ApiResponseEnum.OK, null);
    }

    public static <T> ApiResponse<T> success(ApiResponseEnumInterface apiResponseEnum) {
        return ApiResponse.success(apiResponseEnum, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.success(ApiResponseEnum.OK, data);
    }

    public static <T> ApiResponse<T> fail(ApiResponseEnumInterface apiResponseEnum, T data) {
        return new ApiResponse<>(false, apiResponseEnum, data);
    }

    public static <T> ApiResponse<T> fail() {
        return ApiResponse.fail(ApiResponseEnum.INTERNAL_SERVER_ERROR, null);
    }

    public static <T> ApiResponse<T> fail(ApiResponseEnumInterface apiResponseEnum) {
        return ApiResponse.fail(apiResponseEnum, null);
    }

    public static <T> ApiResponse<T> fail(T data) {
        return ApiResponse.fail(ApiResponseEnum.INTERNAL_SERVER_ERROR, data);
    }

}
