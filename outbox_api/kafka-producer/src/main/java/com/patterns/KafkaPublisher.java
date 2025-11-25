package com.patterns;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class KafkaPublisher {

    @Value(value = "${spring.kafka.topic}")
    private String topicName;

    private final KafkaTemplate<String, UserEvents> kafkaTemplate;

    public KafkaPublisher(KafkaTemplate<String, UserEvents> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public boolean sendMessage(UserEvents msg) {
        try {
            SendResult<String, UserEvents> result = kafkaTemplate.send(topicName, msg.id_event.toString(), msg).get();

            return Objects.nonNull(result);
        } catch (Exception exception) {
            return false;
        }
    }
}
