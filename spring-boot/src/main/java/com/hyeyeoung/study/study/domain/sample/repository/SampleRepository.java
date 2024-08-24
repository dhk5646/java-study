package com.hyeyeoung.study.study.domain.sample.repository;

import com.hyeyeoung.study.study.domain.sample.database.Samples;
import com.hyeyeoung.study.study.domain.sample.entity.Sample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SampleRepository {

    public Sample select(String id) {
        return Samples.select(id);
    }

    public List<Sample> select() {
        return Samples.select();
    }
}
