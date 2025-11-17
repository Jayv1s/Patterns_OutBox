package com.patterns.outbox_api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    @JsonProperty("total_money_transferred")
    int money;

    public Response(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
