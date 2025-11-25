package com.patterns;


import com.patterns.entity.UserEntity;
import com.patterns.entity.UsersEventsEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersistenceLayer {

    public EntityManager entityManager;

    public PersistenceLayer(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public User insertUser(User user) {
        UserEntity userEntity = new UserEntity(user.getName(), user.getMoney());
        entityManager.persist(userEntity);

        user.setId(userEntity.getId());

        return user;
    }

    @Transactional
    public void insertUserEvents(User user) {
        UsersEventsEntity usersEventsEntity = new UsersEventsEntity(user.getId(), user.getMoney(), EventStatus.PENDING.name());
        entityManager.persist(usersEventsEntity);
    }

    @Transactional
    public void updateUserEvents(UserEvents userEvents) {
        UsersEventsEntity usersEventsEntity = new UsersEventsEntity(userEvents.getId_event(), userEvents.getId_user(), userEvents.getMoney(), userEvents.getStatus());

        entityManager.merge(usersEventsEntity);
    }
}
