package com.patterns.outbox_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_events")
public class UsersEventsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_event;

    private Long id_user;

    private Long money;

    private String status;

    public UsersEventsEntity(Long id_user, Long money, String status) {
        this.id_user = id_user;
        this.money = money;
        this.status = status;
    }

    public enum EventStatus {
        PENDING,
        IN_PROCESS,
        COMPLETED;
    }
}
