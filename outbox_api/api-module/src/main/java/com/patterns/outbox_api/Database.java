package com.patterns.outbox_api;

import com.patterns.User;
import com.patterns.outbox_api.entity.UserEntity;
import com.patterns.outbox_api.entity.UsersEventsEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class Database {

    public EntityManager entityManager;

    public Database(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User insertUser(User user) {
        UserEntity userEntity = new UserEntity(user.getName(), user.getMoney());
        entityManager.persist(userEntity);

        user.setId(userEntity.getId());

        return user;
    }

    public void insertUserEvents(User user) {
        UsersEventsEntity usersEventsEntity = new UsersEventsEntity(user.getId(), user.getMoney(), UsersEventsEntity.EventStatus.PENDING.name());
        entityManager.persist(usersEventsEntity);
    }
}
