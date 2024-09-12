package com.hyeyeong.study.common.slack.webhook;

import com.hyeyeoung.study.common.slack.webhook.SlackWebhookClient;
import com.hyeyeoung.study.common.slack.webhook.dto.SlackWebhookRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SlackWebhookClientTest {

    @Test
    @DisplayName("슬랙 웹훅 전송 테스트")
    public void testCase1() {

        // given
        String url = "";

        SlackWebhookRequest data = new SlackWebhookRequest("TEST");

        // when
        SlackWebhookClient.postMessage(url, data);

        // then

    }
}
