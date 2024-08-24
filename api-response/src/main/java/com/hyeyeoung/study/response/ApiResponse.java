package com.hyeyeoung.study.response;

import com.hyeyeoung.study.response.enums.ApiResponseEnumInterface;
import com.hyeyeoung.study.response.enums.ApiResponseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {
    private final Integer code;
    private final String message;
    private final Boolean isSuccessful;
    private final T data;

    public ApiResponse (ApiResponseEnumInterface apiResponseEnum, T data) {
        this.code = apiResponseEnum.getCode();
        this.message = apiResponseEnum.getMessage();
        this.isSuccessful = apiResponseEnum.isSuccessful();
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ApiResponseEnum.SUCCESS, data);
    }

    public static <T> ApiResponse<T> success() {
        return ApiResponse.success(null);
    }

    public static <T> ApiResponse<T> fail(ApiResponseEnumInterface apiResponseEnumInterface, T data) {
        return new ApiResponse<>(apiResponseEnumInterface, data);
    }

    public static ApiResponse<Void> fail(ApiResponseEnumInterface apiResponseEnumInterface) {
        return ApiResponse.fail(apiResponseEnumInterface, null);
    }
}
