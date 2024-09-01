package com.hyeyeoung.study.domain.sample.service;

import com.hyeyeoung.study.domain.sample.dto.SampleDto;
import com.hyeyeoung.study.domain.sample.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;

    public SampleDto select(Long sampleSeq) {
        return SampleDto.from(sampleRepository.findBySampleSeq(sampleSeq));
    }

    public List<SampleDto> selectAll() {
        return sampleRepository.findAllBy().stream()
                .map(SampleDto::from)
                .collect(Collectors.toList());
    }
}
