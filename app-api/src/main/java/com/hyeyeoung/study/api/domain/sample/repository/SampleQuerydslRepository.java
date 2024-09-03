package com.hyeyeoung.study.api.domain.sample.repository;

import com.hyeyeoung.study.api.domain.sample.entity.QSample;
import com.hyeyeoung.study.api.domain.sample.entity.Sample;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class SampleQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Sample findBySampleSeqByQuerydsl(Long sampleSeq) {
        return jpaQueryFactory.selectFrom(QSample.sample)
                .where(QSample.sample.sampleSeq.eq(sampleSeq))
                .fetchFirst();
    }
}
