package com.hyeyeoung.study.appbatch.domain.techblogscrap.job;

import com.hyeyeoung.study.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.scraper.TechBlogScraper;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.scraper.TechBlogScraperFactory;
import com.hyeyeoung.study.common.slack.webhook.SlackWebhookClient;
import com.hyeyeoung.study.common.slack.webhook.dto.SlackWebhookRequest;
import com.hyeyeoung.study.domain.slack.entity.repository.SlackWebhookRepository;
import com.hyeyeoung.study.domain.slack.enums.SlackWebhookEnum;
import com.hyeyeoung.study.domain.techblog.entity.TechBlogPost;
import com.hyeyeoung.study.domain.techblog.repository.TechBlogPostJpaRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TechBlogScrapProcessor implements ItemProcessor<TechBlogScrapEnum, List<TechBlogPost>> {

    private final TechBlogScraperFactory techBlogScraperFactory;

    private final TechBlogPostJpaRepository techBlogPostRepository;
    private final SlackWebhookRepository slackWebhookRepository;

    @Override
    public List<TechBlogPost> process(@Nonnull TechBlogScrapEnum techBlogScrapEnum) {

        log.info("start scrap process");

        TechBlogScraper techBlogScraper = techBlogScraperFactory.getTechBlogScraper(techBlogScrapEnum);

        List<TechBlogPost> techBlogPosts = techBlogScraper.scrap(techBlogScrapEnum);

        return techBlogPosts.stream()
                .filter(this::sendSlackWebhook)
                .collect(Collectors.toList());

    }

    private boolean sendSlackWebhook(TechBlogPost techBlogPost) {
        if (techBlogPostRepository.existsByUrl(techBlogPost.getUrl())) return false; // 중복 데이터는 전송 제외

        try {
            SlackWebhookClient.postMessage(this.getUrl(), this.createWebhookRequest(techBlogPost));
            return true; // 메시지 전송 성공
        } catch (RuntimeException e) {
            // 예외 발생 시 로깅
            log.error("Failed to send message for post: {}", techBlogPost.getTitle(), e);
            return false; // 메시지 전송 실패
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
