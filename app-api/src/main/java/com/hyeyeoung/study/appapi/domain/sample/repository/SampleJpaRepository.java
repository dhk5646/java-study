package com.hyeyeoung.study.appapi.domain.sample.repository;

import com.hyeyeoung.study.appapi.domain.sample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleJpaRepository extends JpaRepository<Sample, Long> {

    // Query Method
    Sample findBySampleSeq(Long sampleSeq);

    List<Sample> findAllBy();

    // JPQL
    @Query("SELECT s FROM Sample s WHERE s.sampleSeq = :sampleSeq")
    Sample findBySampleSeqByJPQL(@Param("sampleSeq") Long sampleSeq);

    // Native
    @Query(value = "SELECT s.* FROM Samples s WHERE s.sampleSeq = :sampleSeq", nativeQuery = true)
    Sample findBySampleSeqByNative(@Param("sampleSeq") Long sampleSeq);
}
