package com.patterns.outbox_api;

import com.patterns.PersistenceLayer;
import com.patterns.User;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class Controller {

    private final PersistenceLayer persistenceLayer;

    public Controller(PersistenceLayer persistenceLayer) {
        this.persistenceLayer = persistenceLayer;
    }

    @PostMapping("/user")
    @Transactional
    ResponseEntity<?> postUser(@RequestBody User user) {
        this.persistenceLayer.insertUser(user);
        this.persistenceLayer.insertUserEvents(user);

        return ResponseEntity.created(URI.create("")).build();
    }

    @GetMapping("/total_amount")
    ResponseEntity<Response> getTotalAmount() {
        int responseTotalMoney = 1000; //Call database view to get total amount summed up

        Response response = new Response(responseTotalMoney);

        return ResponseEntity.ok(response);
    }
}
