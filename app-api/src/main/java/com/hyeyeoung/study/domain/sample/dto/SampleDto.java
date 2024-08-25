package com.hyeyeoung.study.domain.sample.dto;

import com.hyeyeoung.study.domain.sample.entity.Sample;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SampleDto {
    private String column1;
    private String column2;

    public static SampleDto from(Sample sample) {
        return new SampleDto(sample.getColumn1(), sample.getColumn2());
    }
}
