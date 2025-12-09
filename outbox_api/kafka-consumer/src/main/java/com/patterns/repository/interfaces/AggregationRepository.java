package com.patterns.repository.interfaces;

public interface AggregationRepository {
    void updateUserAggregations(Long userId, Long amount);

    void updateGlobalAggregation(long amount);
}
