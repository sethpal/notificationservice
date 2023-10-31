package com.sepal.notificationservice.controlers;

import com.sepal.notificationservice.dtos.NotificationDto;
import com.sepal.notificationservice.producer.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class JsonMessageController {
    private final RabbitMQJsonProducer jsonProducer;

    public JsonMessageController(RabbitMQJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }


    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody NotificationDto user)
    {
        jsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json Message Sent to RabbitMQ..............");

    }
}
