package com.hyeyeoung.study.domain.sample.repository;

import com.hyeyeoung.study.domain.sample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {

    Sample findBySampleSeq(Long sampleSeq);

    List<Sample> findAllBy();

}
