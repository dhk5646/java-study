package com.hyeyeoung.study.common.ehcache.config;

import com.hyeyeoung.study.common.ehcache.config.expiry.DefaultExpiry;
import com.hyeyeoung.study.common.ehcache.constants.EhcacheConfigurationConstants;
import com.hyeyeoung.study.domain.sample.entity.Sample;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.event.EventType;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import java.util.HashMap;
import java.util.Map;


@EnableCaching
@Configuration
public class EhcacheConfig {

    /**
     * ehcache manager 를 활용하여 jcache manager 로 생성하는 방식이다.
     * 장점
     * - ehcache 에서는 다양한 토폴리지 (heap, offheap, disk) 를 사용할 수 있는데 이를 동일하게 사용하면서 jcache manager 를 사용할 수 있다.
     */
    @Bean
    public CacheManager cacheManager() {
        // ehcache 제공자를 생성한다.
        EhcacheCachingProvider ehcacheCachingProvider = (EhcacheCachingProvider) Caching.getCachingProvider();

        // ehcache configuration 목록을 생성한다.
        Map<String, CacheConfiguration<?, ?>> cacheConfigurationMap = getCacheConfigurationMap();

        // ehcache 제공자와 ehcache 목록을 이용하여 ehcache 기본 설정 객체를 생성한다.
        DefaultConfiguration defaultConfiguration = new DefaultConfiguration(cacheConfigurationMap, ehcacheCachingProvider.getDefaultClassLoader());

        // ehcache 제공자를 이용하여 jache manager 를 생성한다.
        javax.cache.CacheManager cacheManager = ehcacheCachingProvider.getCacheManager(ehcacheCachingProvider.getDefaultURI(), defaultConfiguration);

        // jcache manager 를 spring cache manager 로 변환한다.
        return new JCacheCacheManager(cacheManager);
    }

    private Map<String, CacheConfiguration<?, ?>> getCacheConfigurationMap() {
        CacheEventListenerConfigurationBuilder cacheEventListenerConfigurationBuilder = getCacheEventListenerConfigurationBuilder();

        Map<String, CacheConfiguration<?, ?>> cacheMap = new HashMap<>();

        cacheMap.put(EhcacheConfigurationConstants.SAMPLE_CACHE, createSampleEhCache(cacheEventListenerConfigurationBuilder));

        return cacheMap;
    }


    // Ehcache 에서 사용할 이벤트 리스너
    // 캐시가 생성, 수정, 만료, 제거 될 때 호출
    private CacheEventListenerConfigurationBuilder getCacheEventListenerConfigurationBuilder() {
        return CacheEventListenerConfigurationBuilder
                .newEventListenerConfiguration(
                        new EhcacheEventListener(),
                        EventType.CREATED,
                        EventType.UPDATED,
                        EventType.EXPIRED,
                        EventType.REMOVED
                ).unordered()
                .asynchronous();
    }

    // Ehcache 생성
    private CacheConfiguration<Long, Sample> createSampleEhCache(CacheEventListenerConfigurationBuilder cacheEventListenerConfigurationBuilder) {
        return CacheConfigurationBuilder
                .newCacheConfigurationBuilder(
                        Long.class,
                        Sample.class,
                        ResourcePoolsBuilder.heap(10L)) // 캐시의 키와 값의 유형을 지정하고, 캐시에 사용할 메모리 리소스를 정의합니다. entries 변수는 캐시에 허용되는 최대 엔트리 수를 나타내며, 이는 메모리 리소스를 할당하는 데 사용됩니다.
                .withExpiry(new DefaultExpiry()) // 캐시 엔트리의 만료 방법을 설정
                .withDispatcherConcurrency(2) // 캐시 이벤트를 처리하는 디스패처의 동시 작업 처리량을 제어합니다.
                .withDefaultEventListenersThreadPool() // 이벤트 리스너가 실행될 기본 스레드 풀을 사용하도록 설정합니다.
                .withService(cacheEventListenerConfigurationBuilder) // 캐시 이벤트 리스너 설정
                .build();
    }

//    /**
//     * 기본적인 캐시매니저
//     * 1. ehcache 를 jcache 로 래핑한 후 jcache manager 를 사용하는 방식이다.
//     * */
//    @Bean
//    public CacheManager cacheManager() {
//
//        // jcache manager 를 생성한다.
//        javax.cache.CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
//
//        CacheEventListenerConfigurationBuilder cacheEventListenerConfigurationBuilder = getCacheEventListenerConfigurationBuilder();
//
//        // ehcache configuration 을 생성한다.
//        CacheConfiguration<String, Person> personEhCacheConfiguration = createPersonEhCache(cacheEventListenerConfigurationBuilder);
//
//        // ehcache configuration 을 jcache configuration 으로 변환한다.
//        javax.cache.configuration.Configuration<String, Person> personJCachConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(personEhCacheConfiguration);
//
//        // jcache 를 생성한다.
//        cacheManager.createCache(EhCacheConfigurationConstants.PERSON_CACHE, personJCachConfiguration);
//
//        // jcache manager 를 spring cache manager 로 변환한다.
//        return new JCacheCacheManager(cacheManager);
//    }

}
