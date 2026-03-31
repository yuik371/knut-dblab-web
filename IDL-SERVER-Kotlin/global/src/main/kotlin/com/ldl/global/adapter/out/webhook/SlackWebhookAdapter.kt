package com.ldl.global.adapter.out.webhook

import com.slack.api.Slack
import com.slack.api.model.admin.AppActivity
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class SlackWebhookAdapter(
    private val slackClient: Slack = Slack.getInstance(),
    @Value("\${webhook.slack.url}") private val slackWebhookUrl: String
) : WebhookPort {

    override fun notifySlack(slackMessage: SlackMessage) {
        try {
            val message = "*_${slackMessage.type.title}_* :rotating_light:  \n" +
                    slackMessage.content

            val payload = AppActivity.Payload.builder()
                .text(message)
                .build()
            slackClient.send(slackWebhookUrl, payload)

            Thread.sleep(10000)
        } catch (e: IOException) {
            throw RuntimeException("슬랙 에러 발생 : {}", e)
        }
    }

}