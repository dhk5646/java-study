package com.hyeyeoung.study.common.slack.webhook;

import com.hyeyeoung.study.common.slack.webhook.dto.SlackWebhookRequest;
import lombok.experimental.UtilityClass;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@UtilityClass
public final class SlackWebhookClient {

    public static void postMessage(String url, SlackWebhookRequest request) {
        WebClient.builder()
                .baseUrl(url)
                .build()
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(), // 4xx 또는 5xx 에러 처리
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    // TODO: 에러처리 보완
                                    // 에러 메시지를 포함하여 예외를 발생
                                    return Mono.error(new RuntimeException("Slack API error: " + errorBody));
                                })
                )
                .bodyToMono(String.class)
                .block();
    }
}
