package com.hyeyeoung.study.response.exception;

import com.hyeyeoung.study.response.ApiResponse;
import com.hyeyeoung.study.response.enums.ApiResponseEnumInterface;
import com.hyeyeoung.study.response.enums.HttpStatusEnum;
import lombok.Getter;

@Getter
public abstract class ApiResponseRuntimeException extends RuntimeException {
    private final HttpStatusEnum httpStatus;
    private final ApiResponse<Object> apiResponse;

    protected ApiResponseRuntimeException(HttpStatusEnum httpStatus, ApiResponseEnumInterface apiResponseEnum) {
        super(apiResponseEnum.getMessage());
        this.httpStatus = httpStatus;
        this.apiResponse = ApiResponse.fail(apiResponseEnum);
    }

    protected ApiResponseRuntimeException(HttpStatusEnum httpStatus, ApiResponseEnumInterface apiResponseEnum, Object data) {
        super(apiResponseEnum.getMessage());
        this.httpStatus = httpStatus;
        this.apiResponse = ApiResponse.fail(apiResponseEnum, data);
    }

    public Integer getHttpStatusCode() {
        return this.httpStatus.getValue();
    }
}
