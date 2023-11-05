package com.sepal.notificationservice.consumer;

import com.sepal.notificationservice.dtos.ConsumerResponseDto;
import com.sepal.notificationservice.dtos.NotificationRequestDto;
import com.sepal.notificationservice.dtos.NotificationResponseDto;
import com.sepal.notificationservice.dtos.UserDto;
import com.sepal.notificationservice.models.Notification;
import com.sepal.notificationservice.models.Status;
import com.sepal.notificationservice.models.UserContact;
import com.sepal.notificationservice.repositories.NotificationRepository;
import com.sepal.notificationservice.repositories.UserRepository;
import com.sepal.notificationservice.services.SendEmailService;
import com.sepal.notificationservice.services.SendSMSService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class RabbitMQJsonConsumner {
    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQJsonConsumner.class);
    private final SendEmailService sendEmailService;
    private final UserRepository userRepository;
    private  String notificationStatus;
    private final NotificationRepository notificationRepository;

    private final SendSMSService sendSMSService;
    @Value("${spring.mail.username}")
    private String fromMail;
    @Value("${spring.mail.subject}")
    private String emailSubject;


    public RabbitMQJsonConsumner(SendEmailService sendEmailService
                                 ,SendSMSService sendSMSService
                                 ,UserRepository userRepository
                                 ,NotificationRepository notificationRepository)
    {
        this.sendEmailService = sendEmailService;
        this.sendSMSService=sendSMSService;
        this.userRepository=userRepository;
        this.notificationRepository=notificationRepository;

    }

    //Consumer is subscribed to the Queue
    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(NotificationRequestDto notificationDto){
        LOGGER.info(String .format("Json Message Received --> %s", notificationDto.toString()));

        /*
        In request body I will receive a user id
        For that user id I will fetch the user contact (email address and mobile number) from the user data table
        Send sms and email to those contacts
        */

        UserDto userDto=new UserDto();
        NotificationResponseDto notificationResponseDto=new NotificationResponseDto();
        ConsumerResponseDto consumerResponseDto=new ConsumerResponseDto();

        Optional<UserContact> userOptional;
        userOptional = userRepository.findById(Long.valueOf(notificationDto.getUserid()));

        //Check if user is present in DB or not
        if(userOptional.isEmpty()){
            notificationStatus= String.valueOf(Status.FAILED);
            LOGGER.info("User with "+notificationDto.getUserid()+" not present in DB");
        }
        else {
            userDto.setUserid(userOptional.get().getId());
            userDto.setEmail(userOptional.get().getEmail());
            userDto.setMobile(userOptional.get().getMobile());

            notificationResponseDto=sendEmailService.sendEmail(fromMail, userDto.getEmail(), emailSubject, notificationDto.getMessage());
            consumerResponseDto.setEmailStatus(notificationResponseDto.getStatus());
            String toNumber = "+917417331773"; // this need to be updated once i fetch the user mobile from the db
            //toNumber=userDto.getMobile(); //enable this when reading data from user table
            notificationResponseDto=sendSMSService.sendSMS(toNumber, notificationDto.getMessage());
            consumerResponseDto.setMobileStatus(notificationResponseDto.getStatus());
            Notification notification = new Notification();
            notification.setMessageDetails(notificationDto.getMessage());
            notification.setStatus((Status.DELIVERED));
            notification.setUser_id(notificationDto.getUserid());
            notificationRepository.save(notification);

            notificationStatus = String.valueOf(Status.DELIVERED);
        }

    }



}
