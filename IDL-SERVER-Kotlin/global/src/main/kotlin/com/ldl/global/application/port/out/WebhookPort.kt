package com.ldl.global.application.port.out

interface WebhookPort {
    fun notifySlack(slackMessage: SlackMessage)
}