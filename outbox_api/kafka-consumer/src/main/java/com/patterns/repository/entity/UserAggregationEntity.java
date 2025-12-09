package com.patterns.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "user_aggregations")
public class UserAggregationEntity {
    @Id
    private Long user_id;

    private Long total_money;

    private int transaction_count;

    private Instant last_updated;

    public UserAggregationEntity(Long userId, Long total_money, int transaction_count, Instant last_updated) {
        this.user_id = userId;
        this.total_money = total_money;
        this.transaction_count = transaction_count;
        this.last_updated = last_updated;
    }

    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long userId) {
        this.user_id = userId;
    }

    public Long getTotal_money() {
        return total_money;
    }

    public void setTotal_money(Long total_money) {
        this.total_money = total_money;
    }

    public int getTransaction_count() {
        return transaction_count;
    }

    public void setTransaction_count(int transaction_count) {
        this.transaction_count = transaction_count;
    }

    public Instant getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Instant last_updated) {
        this.last_updated = last_updated;
    }
}
