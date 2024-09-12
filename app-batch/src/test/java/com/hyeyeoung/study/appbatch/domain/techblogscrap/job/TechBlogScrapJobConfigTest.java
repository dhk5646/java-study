package com.hyeyeoung.study.appbatch.domain.techblogscrap.job;

import com.hyeyeoung.study.appbatch.common.constant.JobConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@SpringBatchTest
public class TechBlogScrapJobConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @BeforeEach
    void beforeEach(@Qualifier(value = JobConstants.TECH_BLOG_SCRAP_JOB) Job job) {
        jobLauncherTestUtils.setJob(job);
    }

    @Test
    public void testJob() throws Exception {

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        // then
        assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
    }
}
