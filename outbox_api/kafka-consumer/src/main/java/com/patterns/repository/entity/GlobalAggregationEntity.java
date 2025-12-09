package com.patterns.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "global_aggregation")
public class GlobalAggregationEntity {
    @Id
    private Long id;

    private Long total_money;

    private int transaction_count;

    private Instant last_updated;

    public GlobalAggregationEntity(Long id, Long total_money, int transaction_count, Instant last_updated) {
        this.id = id;
        this.total_money = total_money;
        this.transaction_count = transaction_count;
        this.last_updated = last_updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal_money() {
        return total_money;
    }

    public void setTotal_money(Long total_money) {
        this.total_money = total_money;
    }

    public Instant getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Instant last_updated) {
        this.last_updated = last_updated;
    }

    public int getTransaction_count() {
        return transaction_count;
    }

    public void setTransaction_count(int transaction_count) {
        this.transaction_count = transaction_count;
    }
}
