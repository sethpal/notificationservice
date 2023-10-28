package com.sepal.notificationservice.consumer;

import com.sepal.notificationservice.dtos.User;
import com.sepal.notificationservice.services.SendEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumner {
    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQJsonConsumner.class);
    SendEmailService sendEmailService;

    public RabbitMQJsonConsumner(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    //Consumer is subscribed to the Queue
    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(User user){
        LOGGER.info(String .format("Json Message Received --> %s", user.toString()));
        String to="sethpalster@gmail.com";
        String sub="Test";
        String body="Hi This is just a test mail";
        sendEmailService.sendEmail(to,user.getFirstName(),user.getLastName());
    }

}
