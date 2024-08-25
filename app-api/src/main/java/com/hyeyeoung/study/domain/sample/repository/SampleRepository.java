package com.hyeyeoung.study.domain.sample.repository;

import com.hyeyeoung.study.domain.sample.database.Samples;
import com.hyeyeoung.study.domain.sample.entity.Sample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SampleRepository {

    public Sample select(String column1) {
        return Samples.select(column1);
    }

    public List<Sample> select() {
        return Samples.select();
    }

    public void delete(String column1) {
        Samples.delete(column1);
    }
}
