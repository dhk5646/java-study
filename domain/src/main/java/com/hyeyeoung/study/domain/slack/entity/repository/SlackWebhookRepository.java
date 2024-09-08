package com.hyeyeoung.study.domain.slack.entity.repository;

import com.hyeyeoung.study.domain.slack.entity.SlackWebhook;
import com.hyeyeoung.study.domain.slack.enums.SlackWebhookEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SlackWebhookRepository extends JpaRepository<SlackWebhook, Long> {

    Optional<SlackWebhook> findBySlackWebhookEnum(SlackWebhookEnum slackWebhookEnum);

}
