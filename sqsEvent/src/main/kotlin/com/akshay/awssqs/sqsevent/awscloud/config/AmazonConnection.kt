package com.akshay.awssqs.sqsevent.awscloud.config

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AmazonConnection(
    @Value("\${aws.connection.secretkey}") private val secretKey: String,
    @Value("\${aws.connection.accesskey}") private val accessKey: String
) {

    fun getAWSCredentials(): AWSCredentials {

        return BasicAWSCredentials(accessKey, secretKey)
    }
}