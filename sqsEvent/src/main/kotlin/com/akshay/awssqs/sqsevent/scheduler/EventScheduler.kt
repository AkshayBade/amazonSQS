package com.akshay.awssqs.sqsevent.scheduler

import org.springframework.stereotype.Component
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Component
class EventScheduler(private val eventRunnable: EventRunnable) {

    fun schedule() {
        val scheduledExecutorService = Executors.newScheduledThreadPool(1)

        scheduledExecutorService.scheduleAtFixedRate(eventRunnable, 1, 5, TimeUnit.MINUTES)
    }
}