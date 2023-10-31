package com.sepal.notificationservice.consumer;

import com.sepal.notificationservice.dtos.NotificationDto;
import com.sepal.notificationservice.services.SendEmailService;
import com.sepal.notificationservice.services.SendSMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumner {
    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQJsonConsumner.class);
    private final SendEmailService sendEmailService;
    private final SendSMSService sendSMSService;
    @Value("${spring.mail.username}")
    private String fromMail;
    @Value("${spring.mail.subject}")
    private String emailSubject;


    public RabbitMQJsonConsumner(SendEmailService sendEmailService,SendSMSService sendSMSService) {
        this.sendEmailService = sendEmailService;
        this.sendSMSService=sendSMSService;

    }

    //Consumer is subscribed to the Queue
    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(NotificationDto notificationDto){
        LOGGER.info(String .format("Json Message Received --> %s", notificationDto.toString()));

        /*
        In request body I will receive a user id
        For that user id I will fetch the user contact (email address and mobile number) from the user data table
        Send sms and email to those contacts
        */

        sendEmailService.sendEmail(fromMail,notificationDto.getUserid(), emailSubject, notificationDto.getMessage());

        String toNumber="+917417331773"; // this need to be updated once i fetch the user mobile from the db
        sendSMSService.sendSMS(toNumber,notificationDto.getMessage());

    }

}
