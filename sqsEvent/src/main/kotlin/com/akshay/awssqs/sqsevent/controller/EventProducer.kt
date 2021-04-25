package com.akshay.awssqs.sqsevent.controller

import com.akshay.awssqs.sqsevent.scheduler.EventScheduler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EventProducer(private val scheduler: EventScheduler) {

    @GetMapping("/startProducer")
    fun startProducer(): String {
        scheduler.schedule()

        return "Scheduled event!!!"
    }
}