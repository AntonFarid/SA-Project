package com.event.participant_service.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "event-topic", groupId = "participant-group")
    public void listen(String message) {
        System.out.println("Received message from Kafka: " + message);
    }
}
