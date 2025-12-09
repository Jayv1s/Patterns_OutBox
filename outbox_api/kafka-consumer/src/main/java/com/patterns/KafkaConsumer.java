package com.patterns;

import com.patterns.repository.interfaces.AggregationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private final AggregationRepository aggregationRepository;

    public KafkaConsumer(AggregationRepository aggregationRepository) {
        this.aggregationRepository = aggregationRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(@Payload UserEvents userEvents,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.OFFSET) long offset) {
        try {
            logger.info("Received message from topic: {}, offset: {} - Message: {}",
                    topic, offset, userEvents);

            //TODO: implement a idempotency logic/layer;

            aggregationRepository.updateUserAggregations(userEvents.getId_user(), userEvents.getMoney());
            aggregationRepository.updateGlobalAggregation(userEvents.getMoney());
        } catch (Exception e) {
            logger.error("Error processing message", e);
        }
    }
}
