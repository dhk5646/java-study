package com.hyeyeoung.study.ehcache.config;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

@Slf4j
public class EhcacheEventListener implements CacheEventListener<Object, Object> {

    @Override
    public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
        // 캐시 이벤트를 처리하는 로직을 여기에 작성합니다.
        log.info("Cache Event Received: " + cacheEvent.getType());
    }
}

