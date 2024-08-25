package com.hyeyeoung.study.api_response.controller;

import com.hyeyeoung.study.api_response.dto.FailResponseData;
import com.hyeyeoung.study.domain.sample.dto.SampleDto;
import com.hyeyeoung.study.domain.sample.service.SampleService;
import com.hyeyeoung.study.response.ApiResponse;
import com.hyeyeoung.study.response.enums.ApiResponseEnum;
import com.hyeyeoung.study.response.exception.ApiResponseRuntimeException;
import com.hyeyeoung.study.response.exception.ForbiddenRuntimeException;
import com.hyeyeoung.study.response.exception.ServerErrorRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-response")
public class ApiResponseSampleController {

    private final SampleService sampleService;

    @GetMapping(value = "/success/{column1}")
    public ApiResponse<SampleDto> selectSampleBySuccess(@PathVariable String column1) {
        return ApiResponse.success(sampleService.select(column1));
    }

    @GetMapping(value = "/success/list")
    public ApiResponse<List<SampleDto>> selectSampleListBySuccess() {
        return ApiResponse.success(sampleService.select());
    }

    @GetMapping(value = "/fail")
    public ApiResponse<Void> selectSampleByFail() {
        return ApiResponse.fail(ApiResponseEnum.NOT_EXIST);
    }

    @GetMapping(value = "/fail/server-error-exception")
    public ResponseEntity<ApiResponse<Object>> serverErrorException() {
        try {
            throw new ServerErrorRuntimeException(ApiResponseEnum.NOT_EXIST);
        } catch (ApiResponseRuntimeException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(e.getApiResponse());
        }
    }

    @GetMapping(value = "/fail/forbidden-exception")
    public ResponseEntity<ApiResponse<Object>> forbiddenException() {
        try {
            throw new ForbiddenRuntimeException(ApiResponseEnum.NOT_EXIST);
        } catch (ApiResponseRuntimeException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(e.getApiResponse());
        }
    }
}
