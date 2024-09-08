package com.hyeyeoung.study.appbatch.domain.techblogscrap.job;

import com.hyeyeoung.study.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Queue;

@Component
@RequiredArgsConstructor
@Slf4j
public class TechBlogScrapReader implements ItemReader<TechBlogScrapEnum>, StepExecutionListener {

    private Queue<TechBlogScrapEnum> techBlogQueue;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        techBlogQueue = new LinkedList<>(EnumSet.allOf(TechBlogScrapEnum.class));
    }

    @Override
    public TechBlogScrapEnum read() {

        log.info("start scrap reader");

        return techBlogQueue.poll();
    }
}
