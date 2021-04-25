package com.akshay.awssqs.sqsevent.awscloud.sqsservice

import com.akshay.awssqs.sqsevent.awscloud.config.AmazonConnection
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import com.amazonaws.services.sqs.model.CreateQueueRequest
import com.amazonaws.services.sqs.model.CreateQueueResult
import com.amazonaws.services.sqs.model.QueueDoesNotExistException
import org.springframework.stereotype.Service

@Service
class SQSProvider(private val amazonConnection: AmazonConnection) {

    fun getAwsSqs(): AmazonSQS {
        return AmazonSQSClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(amazonConnection.getAWSCredentials()))
            .withRegion(Regions.US_EAST_1)
            .build()
    }

    fun getQueueUrlName(queueName: String): String {
        val awsSqs = getAwsSqs()

        return try {
            val queueUrlResult = awsSqs.getQueueUrl(queueName)
            queueUrlResult.queueUrl
        } catch (e: QueueDoesNotExistException) {
            val createQueue = createQueueResult(queueName, awsSqs)
            createQueue.queueUrl
        }
    }

    private fun createQueueResult(
        queueName: String,
        awsSqs: AmazonSQS
    ): CreateQueueResult {
        val createQueueRequest = CreateQueueRequest(queueName)
        return awsSqs.createQueue(createQueueRequest)
    }
}