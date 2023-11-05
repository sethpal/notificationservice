package com.sepal.notificationservice.controllers;

import com.sepal.notificationservice.consumer.RabbitMQJsonConsumner;
import com.sepal.notificationservice.dtos.NotificationHistoryResponseDto;
import com.sepal.notificationservice.dtos.NotificationRequestDto;
import com.sepal.notificationservice.dtos.NotificationResponseDto;
import com.sepal.notificationservice.models.Status;
import com.sepal.notificationservice.producer.RabbitMQJsonProducer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
public class JsonMessageController {
    private final RabbitMQJsonProducer jsonProducer;
   private final RabbitMQJsonConsumner rabbitMQJsonConsumner;

    public JsonMessageController(RabbitMQJsonProducer jsonProducer,RabbitMQJsonConsumner rabbitMQJsonConsumner) {
        this.jsonProducer = jsonProducer;
        this.rabbitMQJsonConsumner=rabbitMQJsonConsumner;
    }


    @PostMapping("/publish")
    public NotificationResponseDto sendJsonMessage(@RequestBody NotificationRequestDto user)
    {
        String notification_added_in_Queue=null;
        NotificationResponseDto notificationResponseDto =new NotificationResponseDto();
        notification_added_in_Queue= jsonProducer.sendJsonMessage(user);
        notificationResponseDto.setStatus(notification_added_in_Queue);
        return notificationResponseDto;
    }
}
