package com.hyeyeoung.study.common.slack.webhook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <a href="https://api.slack.com/messaging/webhooks">...</a>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SlackWebhookRequest {

    private String text;

    public static SlackWebhookRequest of(String text) {
        return new SlackWebhookRequest(text);
    }
}
