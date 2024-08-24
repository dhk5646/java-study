package com.hyeyeoung.study.study.domain.sample.service;

import com.hyeyeoung.study.study.domain.sample.dto.SampleDto;
import com.hyeyeoung.study.study.domain.sample.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;

    public SampleDto select(String id) {
        return SampleDto.from(sampleRepository.select(id));
    }

    public List<SampleDto> select() {
        return sampleRepository.select().stream()
                .map(SampleDto::from)
                .collect(Collectors.toList());
    }
}
