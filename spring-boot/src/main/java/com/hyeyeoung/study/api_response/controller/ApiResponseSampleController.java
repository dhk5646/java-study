package com.hyeyeoung.study.api_response.controller;

import com.hyeyeoung.study.domain.sample.dto.SampleDto;
import com.hyeyeoung.study.domain.sample.service.SampleService;
import com.hyeyeoung.study.response.ApiResponse;
import com.hyeyeoung.study.response.enums.ApiResponseEnum;
import com.hyeyeoung.study.response.exception.ForbiddenRuntimeException;
import com.hyeyeoung.study.response.exception.ServerErrorRuntimeException;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse<Void> serverErrorException() {
        throw new ServerErrorRuntimeException(ApiResponseEnum.NOT_EXIST);
    }

    @GetMapping(value = "/fail/forbidden-exception")
    public ApiResponse<Void> forbiddenException() {
        throw new ForbiddenRuntimeException(ApiResponseEnum.NOT_EXIST);
    }

    @GetMapping(value = "/fail/exception")
    public ApiResponse<Void> exception() throws Exception {
        throw new Exception("occurred exception");
    }
}
