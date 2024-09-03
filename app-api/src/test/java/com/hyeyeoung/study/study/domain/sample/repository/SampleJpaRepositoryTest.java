package com.hyeyeoung.study.study.domain.sample.repository;

import com.hyeyeoung.study.api.domain.sample.entity.Sample;
import com.hyeyeoung.study.api.domain.sample.repository.SampleJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SampleJpaRepositoryTest {

    @Autowired
    SampleJpaRepository sampleJpaRepository;

    @Test
    @DisplayName("query method 조회 테스트")
    void testCase1() {
        // given
        Long sampleSeq = 1L;

        // when
        Sample sample = sampleJpaRepository.findBySampleSeq(sampleSeq);

        // then
        Assertions.assertNotNull(sample);
        Assertions.assertEquals(sampleSeq, sample.getSampleSeq());

    }

    @Test
    @DisplayName("JPQL 조회 테스트")
    void testCase2() {
        // given
        Long sampleSeq = 1L;

        // when
        Sample sample = sampleJpaRepository.findBySampleSeqByJPQL(sampleSeq);

        // then
        Assertions.assertNotNull(sample);
        Assertions.assertEquals(sampleSeq, sample.getSampleSeq());

    }

    @Test
    @DisplayName("Native 조회 테스트")
    void testCase3() {
        // given
        Long sampleSeq = 1L;

        // when
        Sample sample = sampleJpaRepository.findBySampleSeqByNative(sampleSeq);

        // then
        Assertions.assertNotNull(sample);
        Assertions.assertEquals(sampleSeq, sample.getSampleSeq());

    }
}
