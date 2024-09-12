package com.hyeyeoung.study.appapi.common.exception;

import com.hyeyeoung.study.common.response.ApiResponse;
import com.hyeyeoung.study.common.response.enums.ApiResponseEnum;
import com.hyeyeoung.study.common.response.exception.ApiResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiResponseException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Object> handleApiResponseException(ApiResponseException ex) {
        log.error("handleApiResponseRuntimeException: {}", ex.getMessage(), ex);
        return ex.getApiResponse();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Void> handleException(Exception ex) {
        log.error("handleException: {}", ex.getMessage(), ex);
        return ApiResponse.fail(ApiResponseEnum.INTERNAL_SERVER_ERROR);
    }
}
