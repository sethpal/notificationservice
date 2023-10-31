package com.sepal.notificationservice.consumer;

import com.sepal.notificationservice.config.TwilioConfig;
import com.sepal.notificationservice.dtos.User;
import com.sepal.notificationservice.services.SendEmailService;
import com.sepal.notificationservice.services.SendSMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumner {
    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQJsonConsumner.class);
    private final SendEmailService sendEmailService;
    private final SendSMSService sendSMSService;


    public RabbitMQJsonConsumner(SendEmailService sendEmailService,SendSMSService sendSMSService) {
        this.sendEmailService = sendEmailService;
        this.sendSMSService=sendSMSService;

    }

    //Consumer is subscribed to the Queue
    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(User user){
        LOGGER.info(String .format("Json Message Received --> %s", user.toString()));
        String to="sethpalster@gmail.com";
        String sub="Test";
        String body="Hi This is just a test mail";
       // sendEmailService.sendEmail(to,user.getFirstName(),user.getLastName());

        String toNumber="+917417331773";
        String messageBody="Hello I testing sms service";
        sendSMSService.sendSMS(toNumber,messageBody);

    }

}
