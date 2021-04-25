package com.akshay.awssqs.sqsevent.eventQueue

import com.akshay.awssqs.sqsevent.awscloud.sqsservice.SQSProvider
import com.amazonaws.services.sqs.AmazonSQS
import org.springframework.stereotype.Component

@Component
class QueueProvider(private val sqsProvider: SQSProvider) {

    fun getEventQueue(): AmazonSQS {
        return sqsProvider.getAwsSqs()
    }

    fun getEventQueueUrlName(queueName: String): String {
        return sqsProvider.getQueueUrlName(queueName)
    }

}