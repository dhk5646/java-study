package com.hyeyeoung.study.api.domain.sample.controller;

import com.hyeyeoung.study.api.domain.sample.entity.Sample;
import com.hyeyeoung.study.api.domain.sample.service.SampleEhcacheService;
import com.hyeyeoung.study.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ehcache")
public class EhcacheController {

    private final SampleEhcacheService sampleEhcacheService;

    @GetMapping(value = "/no-cache/{sampleSeq}")
    public Sample getNoEhcacheCommonCode(@PathVariable Long sampleSeq) {
        return sampleEhcacheService.select(sampleSeq);
    }

    @GetMapping(value = "/cache/{sampleSeq}")
    public ApiResponse<Sample> getEhcacheCommonCode(@PathVariable Long sampleSeq) {
        return ApiResponse.success(sampleEhcacheService.selectFromCache(sampleSeq));
    }
}
