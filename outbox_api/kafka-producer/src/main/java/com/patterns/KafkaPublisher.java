package com.patterns;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisher {

    @Value(value = "${spring.kafka.topic}")
    private String topicName;

    private final KafkaTemplate<String, UserEvents> kafkaTemplate;

    public KafkaPublisher(KafkaTemplate<String, UserEvents> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(UserEvents msg) {
        kafkaTemplate.send(topicName, msg.id_event.toString(), msg);
    }
}
