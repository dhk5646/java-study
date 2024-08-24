package com.hyeyeoung.study.study.domain.sample.database;

import com.hyeyeoung.study.response.enums.ApiResponseEnum;
import com.hyeyeoung.study.response.exception.ApplicationRuntimeException;
import com.hyeyeoung.study.study.domain.sample.entity.Sample;

import java.util.ArrayList;
import java.util.List;


public class Samples {

    private static final List<Sample> samples = new ArrayList<>();

    static {
        // 테스트 목적으로 데이터를 초기화 한다.
        samples.add(Sample.of("aks", "악스"));
        samples.add(Sample.of("donguri", "동구리"));
    }

    public static Sample select(String id) {
        if (id == null) {
            throw new ApplicationRuntimeException(ApiResponseEnum.NOT_EXIST);
        }

        return samples.stream()
                .filter(sample -> sample.getColumn1().equals(id))
                .findFirst()
                .orElseGet(Sample::empty);
    }

    public static List<Sample> select() {
        return new ArrayList<>(samples);
    }
}
