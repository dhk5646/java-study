package com.hyeyeoung.study.common.ehcache.config.expiry;


import org.ehcache.expiry.ExpiryPolicy;

import java.time.Duration;
import java.util.function.Supplier;

public class DefaultExpiry implements ExpiryPolicy<Object, Object> {

    private final static Duration DEFAULT_DURATION = Duration.ofMinutes(5); // 5분

    @Override
    public Duration getExpiryForCreation(Object key, Object value) {
        return DEFAULT_DURATION;
    }

    @Override
    public Duration getExpiryForAccess(Object key, Supplier supplier) {
        return DEFAULT_DURATION;
    }

    @Override
    public Duration getExpiryForUpdate(Object key, Supplier supplier, Object value) {
        return DEFAULT_DURATION;
    }
}
