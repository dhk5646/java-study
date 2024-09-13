package com.hyeyeoung.study.appbatch.domain.techblogscrap.job;

import com.hyeyeoung.study.domain.techblog.entity.TechBlogPost;
import com.hyeyeoung.study.domain.techblog.repository.TechBlogPostJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TechBlogScrapWriter implements ItemWriter<List<TechBlogPost>> {

    private final TechBlogPostJpaRepository techBlogPostRepository;

    @Override
    public void write(Chunk<? extends List<TechBlogPost>> chunk) {

        log.info("start scrap reader");

        List<TechBlogPost> techBlogPosts = chunk.getItems().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        techBlogPostRepository.saveAll(techBlogPosts);

    }
}