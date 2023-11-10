package com.sepal.notificationservice.services;

import com.sepal.notificationservice.dtos.NotificationRequestDto;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Data
@Configuration
public class RabbitMQJsonProducerService {


    //private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQJsonProducerService.class);
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.json.routing.key}")
    private String jsonRoutingKey;


    private  RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducerService(RabbitTemplate rabbitTemplate) {
        System.out.println("RabbitMQJsonProducerService Constructor");
       this.rabbitTemplate = rabbitTemplate;
    }

    public String sendJsonMessage(NotificationRequestDto user)
    {
        String message="Notification sent to registered email and mobile, kindly check your email/mobile";
        //LOGGER.info(String.format(message+"--> %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange,jsonRoutingKey,user);
        return message;
    }
}
