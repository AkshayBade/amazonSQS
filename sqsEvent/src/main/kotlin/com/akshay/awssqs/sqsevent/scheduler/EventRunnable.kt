package com.akshay.awssqs.sqsevent.scheduler

import com.akshay.awssqs.sqsevent.eventQueue.QueueProvider
import com.amazonaws.services.sqs.model.SendMessageRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class EventRunnable(private val queueProvider: QueueProvider, @Value("\${queue.name}") private val queueName: String) :
    Runnable {

    override fun run() {
        val eventQueueUrlName = queueProvider.getEventQueueUrlName(queueName)
        val sendMessageRequest =
            SendMessageRequest().withQueueUrl(eventQueueUrlName).withMessageBody("Sample event content")

        val sendMessageResult = queueProvider.getEventQueue().sendMessage(sendMessageRequest)

        println("Message sent: " + sendMessageResult.messageId)
    }
}