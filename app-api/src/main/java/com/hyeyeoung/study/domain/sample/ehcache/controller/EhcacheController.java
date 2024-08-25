package com.hyeyeoung.study.domain.sample.ehcache.controller;

import com.hyeyeoung.study.domain.sample.entity.Sample;
import com.hyeyeoung.study.domain.sample.service.SampleEhcacheService;
import com.hyeyeoung.study.response.ApiResponse;
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

    @GetMapping(value = "/no-cache/{column1}")
    public Sample getNoEhcacheCommonCode(@PathVariable String column1) {
        return sampleEhcacheService.select(column1);
    }

    @GetMapping(value = "/cache/{column1}")
    public ApiResponse<Sample> getEhcacheCommonCode(@PathVariable String column1) {
        return ApiResponse.success(sampleEhcacheService.selectFromCache(column1));
    }
}
