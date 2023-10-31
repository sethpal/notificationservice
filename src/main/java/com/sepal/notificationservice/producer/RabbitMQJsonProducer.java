package com.sepal.notificationservice.producer;

import com.sepal.notificationservice.dtos.NotificationDto;
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

    public void sendJsonMessage(NotificationDto user)
    {
    LOGGER.info(String.format("Json Message Sent--> %s", user.toString()));
    rabbitTemplate.convertAndSend(exchange,jsonRoutingKey,user);
    }
}
