package com.hyeyeoung.study.appbatch.domain.techblogscrap.job;

import com.hyeyeoung.study.appbatch.common.constant.JobConstants;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import com.hyeyeoung.study.domain.techblog.entity.TechBlogPost;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class TechBlogScrapJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    private final TechBlogScrapReader reader;
    private final TechBlogScrapProcessor processor;
    private final TechBlogScrapWriter writer;

    @Bean(name = JobConstants.TECH_BLOG_SCRAP_JOB)
    public Job job(Step techBlogScrapJobStep1) {
        return new JobBuilder(JobConstants.TECH_BLOG_SCRAP_JOB, jobRepository)
                .start(techBlogScrapJobStep1)
                .build();
    }

    @Bean
    public Step techBlogScrapJobStep1() {
        return new StepBuilder("techBlogScrapJobStep1", jobRepository)
                .<TechBlogScrapEnum, List<TechBlogPost>>chunk(1, platformTransactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}