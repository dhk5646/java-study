package com.hyeyeoung.study.api.domain.sample.service;

import com.hyeyeoung.study.api.common.ehcache.constants.EhcacheConfigurationConstants;
import com.hyeyeoung.study.api.domain.sample.entity.Sample;
import com.hyeyeoung.study.api.domain.sample.repository.SampleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SampleEhcacheService {

    private final SampleJpaRepository sampleRepository;

    public Sample select(Long sampleSeq) {
        return sampleRepository.findBySampleSeq(sampleSeq);
    }

    // @Cacheable을 사용하여 캐시에서 값을 가져오는 메서드
    @Cacheable(cacheNames = EhcacheConfigurationConstants.SAMPLE_CACHE, key = "#sampleSeq")
    public Sample selectFromCache(Long sampleSeq) {
        return sampleRepository.findBySampleSeq(sampleSeq);
    }

    // @CachePut을 사용하여 캐시에 값을 추가 또는 갱신하는 메서드
    @CachePut(cacheNames = EhcacheConfigurationConstants.SAMPLE_CACHE, key = "#sampleSeq")
    public Sample updateInCache(Long sampleSeq, String content) {
        Sample sample = this.select(sampleSeq); // selectPersonFromCache 호출 하지 않는 이유는, 프록시 객체의 경우 self-invocation 이 발생하여 @Cacheable AOP 가 동작하지 않음.
        sample.modifyContent(content);

        return sample;
    }

    // @CacheEvict를 사용하여 캐시에서 값을 삭제하는 메서드
    @CacheEvict(cacheNames = EhcacheConfigurationConstants.SAMPLE_CACHE, key = "#sampleSeq")
    public void deleteFromCache(Long sampleSeq) {
        Sample sample = sampleRepository.findBySampleSeq(sampleSeq);
        sampleRepository.delete(sample);
    }
}
