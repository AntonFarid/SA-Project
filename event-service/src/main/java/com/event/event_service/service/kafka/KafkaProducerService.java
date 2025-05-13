package com.event.event_service.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendEventCreatedMessage(String eventName) {
        kafkaTemplate.send("event-topic", "Event Created: " + eventName);
    }
}
