package com.sepal.notificationservice.producer;

import com.sepal.notificationservice.dtos.NotificationRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {


    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQJsonProducer.class);
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.json.routing.key}")
    private String jsonRoutingKey;
    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String sendJsonMessage(NotificationRequestDto user)
    {
        String message="Notification sent to registered email and mobile, kindly check your email/mobile";
        LOGGER.info(String.format(message+"--> %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange,jsonRoutingKey,user);
        return message;
    }
}
