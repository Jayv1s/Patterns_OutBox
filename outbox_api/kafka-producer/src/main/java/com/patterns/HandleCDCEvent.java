package com.patterns;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class HandleCDCEvent {

    private final KafkaPublisher kafkaPublisher;
    private final PersistenceLayer persistenceLayer;

    public HandleCDCEvent(KafkaPublisher kafkaPublisher, PersistenceLayer persistenceLayer) {
        this.kafkaPublisher = kafkaPublisher;
        this.persistenceLayer = persistenceLayer;
    }

    public void handle(String payload) {
        UserEvents userEvents = mapPayload(payload);

        if(Objects.nonNull(userEvents) && userEvents.status.equals(EventStatus.PENDING.name())) {
            userEvents.status = EventStatus.IN_PROCESS.name();
            persistenceLayer.updateUserEvents(userEvents);

            System.out.println(userEvents);
            boolean isSuccess = kafkaPublisher.sendMessage(userEvents);

            if(isSuccess) {
                userEvents.status = EventStatus.COMPLETED.name();
                persistenceLayer.updateUserEvents(userEvents);
            } else {
                userEvents.status = EventStatus.PENDING.name();
                persistenceLayer.updateUserEvents(userEvents);
            }
        }

    }

    private UserEvents mapPayload(String value) {
        Gson gson = new Gson();

        PayloadData payloadData = gson.fromJson(value, PayloadData.class);

        if(Objects.isNull(payloadData.getPayload()) || Objects.isNull(payloadData.getPayload().getAfter())) {
            return null;
        }

        UserEvents userEvents = gson.fromJson(gson.toJson(payloadData.getPayload().getAfter()), UserEvents.class);

        return userEvents;
    }
}
