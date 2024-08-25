package com.hyeyeoung.study.api_response.exception;

import com.hyeyeoung.study.response.ApiResponse;
import com.hyeyeoung.study.response.enums.ApiResponseEnum;
import com.hyeyeoung.study.response.exception.ApiResponseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiResponseRuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleApiResponseRuntimeException(ApiResponseRuntimeException ex) {
        log.error("handleApiResponseRuntimeException: {} - {}", ex.getHttpStatusCode(), ex.getMessage(), ex);
        return ResponseEntity
                .status(ex.getHttpStatusCode())
                .body(ex.getApiResponse());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Void> handleException(Exception ex) {
        log.error("handleException: {}", ex.getMessage(), ex);
        return ApiResponse.fail(ApiResponseEnum.INTERNAL_SERVER_ERROR);
    }
}
