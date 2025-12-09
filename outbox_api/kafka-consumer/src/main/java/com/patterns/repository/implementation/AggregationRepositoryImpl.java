package com.patterns.repository.implementation;

import com.patterns.repository.interfaces.AggregationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AggregationRepositoryImpl implements AggregationRepository {

    private final EntityManager entityManager;

    public AggregationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void updateUserAggregations(Long userId, Long amount) {
        Query query = entityManager.createNativeQuery("""
                INSERT INTO user_aggregations (user_id, total_money, transaction_count, last_updated)
                VALUES(?1, ?2, 1, NOW())
                ON DUPLICATE KEY UPDATE
                    total_money = total_money + ?2,
                    transaction_count = transaction_count + 1,
                    last_updated = NOW()
                """);
        query.setParameter(1, userId);
        query.setParameter(2, amount);

        query.executeUpdate();
    }

    @Override
    @Transactional
    public void updateGlobalAggregation(long amount) {
        Query query = entityManager.createNativeQuery("""
                INSERT INTO global_aggregation (id, total_money, transaction_count, last_updated)
                VALUES(1, ?1, 1, NOW())
                ON DUPLICATE KEY UPDATE
                    total_money = total_money + ?1,
                    transaction_count = transaction_count + 1,
                    last_updated = NOW()
                """);
        query.setParameter(1, amount);

        query.executeUpdate();
    }
}
