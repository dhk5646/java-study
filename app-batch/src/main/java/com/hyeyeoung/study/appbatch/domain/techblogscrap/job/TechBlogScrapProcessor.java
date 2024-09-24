package com.hyeyeoung.study.appbatch.domain.techblogscrap.job;

import com.hyeyeoung.study.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.scraper.TechBlogScraper;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.scraper.TechBlogScraperFactory;
import com.hyeyeoung.study.common.slack.webhook.SlackWebhookClient;
import com.hyeyeoung.study.common.slack.webhook.dto.SlackWebhookRequest;
import com.hyeyeoung.study.domain.slack.entity.repository.SlackWebhookRepository;
import com.hyeyeoung.study.domain.slack.enums.SlackWebhookEnum;
import com.hyeyeoung.study.domain.techblog.entity.TechBlogPost;
import com.hyeyeoung.study.domain.techblog.enums.TechBlogEnum;
import com.hyeyeoung.study.domain.techblog.repository.TechBlogPostRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TechBlogScrapProcessor implements ItemProcessor<TechBlogScrapEnum, List<TechBlogPost>> {

    private final TechBlogScraperFactory techBlogScraperFactory;

    private final TechBlogPostRepository techBlogPostRepository;

    private final SlackWebhookRepository slackWebhookRepository;

    @Override
    public List<TechBlogPost> process(@Nonnull TechBlogScrapEnum techBlogScrapEnum) {

        log.info("start scrap process");

        List<TechBlogPost> techBlogPosts = scrap(techBlogScrapEnum);

        sendSlackWebhook(techBlogPosts);

        return techBlogPosts;

    }

    private List<TechBlogPost> scrap(TechBlogScrapEnum techBlogScrapEnum) {
        TechBlogScraper techBlogScraper = techBlogScraperFactory.getTechBlogScraper(techBlogScrapEnum);

        List<TechBlogPost> techBlogPosts = techBlogScraper.scrap();

        if (CollectionUtils.isEmpty(techBlogPosts)) return Collections.emptyList();

        Set<String> urlSet = getUrlSet(techBlogScrapEnum.getTechBlogEnum());

        return techBlogPosts.stream()
                .filter(techBlogPost -> !urlSet.contains(techBlogPost.getUrl()))
                .collect(Collectors.toList());
    }

    // url 기준으로 동일 데이터에 대해서 필터처리
    private Set<String> getUrlSet(TechBlogEnum techBlogEnum) {
        return techBlogPostRepository.findByTechBlogEnum(techBlogEnum).stream()
                .map(TechBlogPost::getUrl)
                .collect(Collectors.toSet());
    }

    private void sendSlackWebhook(List<TechBlogPost> techBlogPosts) {
        for (TechBlogPost techBlogPost : techBlogPosts) {
            try {
                SlackWebhookClient.postMessage(this.getUrl(), this.createWebhookRequest(techBlogPost));
            } catch (RuntimeException e) {
                // 예외 발생 시 로깅
                log.error("Failed to send message for post: {}", techBlogPost.getTitle(), e);
            }
        }
    }

    private String getUrl() {
        return slackWebhookRepository.findBySlackWebhookEnum(SlackWebhookEnum.TECH_BLOG_SCRAP)
                .orElseThrow(RuntimeException::new)
                .getUrl();
    }

    private SlackWebhookRequest createWebhookRequest(TechBlogPost techBlogPost) {
        String text = String.format("%s%n%s", techBlogPost.getTitle(), techBlogPost.getUrl());
        return SlackWebhookRequest.of(text);
    }

}
