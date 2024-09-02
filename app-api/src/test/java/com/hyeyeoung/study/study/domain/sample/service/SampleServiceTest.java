package com.hyeyeoung.study.study.domain.sample.service;

import com.hyeyeoung.study.domain.sample.dto.SampleDto;
import com.hyeyeoung.study.domain.sample.entity.Sample;
import com.hyeyeoung.study.domain.sample.repository.SampleJpaRepository;
import com.hyeyeoung.study.domain.sample.service.SampleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SampleServiceTest {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private SampleJpaRepository sampleRepository;

    @Test
    void testSelectSample() {
        // given: 샘플 데이터 생성 및 저장
        Sample sample = Sample.of("Sample Content");
        sampleRepository.save(sample);

        // when: SampleSeq로 데이터를 조회
        SampleDto sampleDto = sampleService.select(sample.getSampleSeq());

        // then: 조회된 데이터가 예상된 값인지 검증
        assertNotNull(sampleDto);
        assertEquals("Sample Content", sampleDto.getContent());
    }

    @Test
    void testSelectAllSamples() {
        // given: 샘플 데이터 생성 및 저장
        Sample sample1 = Sample.of("Sample 1");
        Sample sample2 = Sample.of("Sample 2");
        sampleRepository.save(sample1);
        sampleRepository.save(sample2);

        // when: 모든 샘플 데이터 조회
        List<SampleDto> samples = sampleService.selectAll();

        // then: 데이터 개수와 내용을 검증
        assertNotNull(samples);
        org.assertj.core.api.Assertions.assertThat(samples.size()).isPositive();
    }

    @Test
    void testDeleteSample() {
        // given: 샘플 데이터 생성 및 저장
        Sample sample = Sample.of("Sample to Delete");
        sampleRepository.save(sample);

        // when: 샘플 데이터 삭제
        sampleRepository.delete(sample);

        // then: 해당 데이터가 더 이상 존재하지 않음을 검증
        Optional<Sample> deletedSample = sampleRepository.findById(sample.getSampleSeq());
        assertTrue(deletedSample.isEmpty());
    }
}
