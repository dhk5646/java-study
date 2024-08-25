package com.hyeyeoung.study.domain.sample.service;

import com.hyeyeoung.study.common.ehcache.constants.EhcacheConfigurationConstants;
import com.hyeyeoung.study.domain.sample.entity.Sample;
import com.hyeyeoung.study.domain.sample.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SampleEhcacheService {

    private final SampleRepository sampleRepository;

    public Sample select(String column1) {
        return sampleRepository.select(column1);
    }

    // @Cacheable을 사용하여 캐시에서 값을 가져오는 메서드
    @Cacheable(cacheNames = EhcacheConfigurationConstants.SAMPLE_CACHE, key = "#column1")
    public Sample selectFromCache(String column1) {
        return sampleRepository.select(column1);
    }

    // @CachePut을 사용하여 캐시에 값을 추가 또는 갱신하는 메서드
    @CachePut(cacheNames = EhcacheConfigurationConstants.SAMPLE_CACHE, key = "#column2")
    public Sample updateInCache(String column2, String newName) {
        Sample sample = this.select(column2); // selectPersonFromCache 호출 하지 않는 이유는, 프록시 객체의 경우 self-invocation 이 발생하여 @Cacheable AOP 가 동작하지 않음.
        sample.updateColumn1(newName);

        return sample;
    }

    // @CacheEvict를 사용하여 캐시에서 값을 삭제하는 메서드
    @CacheEvict(cacheNames = EhcacheConfigurationConstants.SAMPLE_CACHE, key = "#column1")
    public void deleteFromCache(String column1) {
        sampleRepository.delete(column1);
    }
}
