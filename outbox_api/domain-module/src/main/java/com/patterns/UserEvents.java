package com.patterns;

public class UserEvents {
    public Long id_event;
    public Long id_user;
    public Long money;
    public String status;

    public UserEvents(){}

    public UserEvents(Long id_event, Long id_user, Long money, String status) {
        this.id_event = id_event;
        this.id_user = id_user;
        this.money = money;
        this.status = status;
    }

    public Long getId_event() {
        return id_event;
    }

    public void setId_event(Long id_event) {
        this.id_event = id_event;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserEvents{" +
                "id_event=" + id_event +
                ", id_user=" + id_user +
                ", money=" + money +
                ", status='" + status + '\'' +
                '}';
    }
}
