package com.hyeyeoung.study.appapi.domain.sample.dto.response;

import com.hyeyeoung.study.appapi.domain.sample.dto.SampleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SampleResponse {
    private Long sampleSeq;
    private String content;

    public static SampleResponse from(SampleDto sample) {
        return new SampleResponse(sample.getSampleSeq(), sample.getContent());
    }
}
