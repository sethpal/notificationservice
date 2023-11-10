package com.sepal.notificationservice.controllers;

import com.sepal.notificationservice.dtos.NotificationRequestDto;
import com.sepal.notificationservice.dtos.NotificationResponseDto;
import com.sepal.notificationservice.services.RabbitMQJsonProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class JsonMessageController {

    @Autowired
    private RabbitMQJsonProducerService jsonProducerService;


    public JsonMessageController(RabbitMQJsonProducerService jsonProducerService) {
        this.jsonProducerService = jsonProducerService;
    }

    @PostMapping("/publish")
    public ResponseEntity<NotificationResponseDto> sendJsonMessage(@RequestBody NotificationRequestDto user)
    {
        String notification_added_in_Queue=null;
        NotificationResponseDto notificationResponseDto =new NotificationResponseDto();
        if(user.getMessage()==null || user.getUserid()==null)
        {
            notification_added_in_Queue="Payload is not correct";
            notificationResponseDto.setStatus(notification_added_in_Queue);
            return new ResponseEntity<>(notificationResponseDto, HttpStatus.BAD_REQUEST);
        }
        notification_added_in_Queue= jsonProducerService.sendJsonMessage(user);
        notificationResponseDto.setStatus(notification_added_in_Queue);
        return new ResponseEntity<>(notificationResponseDto, HttpStatus.OK);
    }
}
