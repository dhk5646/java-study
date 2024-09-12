package com.hyeyeoung.study.study.domain.sample.repository;

import com.hyeyeoung.study.appapi.domain.sample.entity.Sample;
import com.hyeyeoung.study.appapi.domain.sample.repository.SampleQuerydslRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SampleQuerydslRepositoryTest {

    @Autowired
    SampleQuerydslRepository sampleQuerydslRepository;

    @Test
    @DisplayName("querydsl 조회 테스트")
    void testCase1() {
        // given
        Long sampleSeq = 1L;

        // when
        Sample sample = sampleQuerydslRepository.findBySampleSeqByQuerydsl(sampleSeq);

        // then
        Assertions.assertNotNull(sample);
        Assertions.assertEquals(sampleSeq, sample.getSampleSeq());

    }
}
