package com.sepal.notificationservice.services;

import com.sepal.notificationservice.config.TwilioConfig;
import com.sepal.notificationservice.dtos.NotificationResponseDto;
import com.sepal.notificationservice.models.Status;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendSMSService {

    @Autowired
    TwilioConfig twilioConfig;

    //private static final Logger LOGGER= LoggerFactory.getLogger(SendSMSService.class);

    public NotificationResponseDto sendSMS(String to, String sms) {
        NotificationResponseDto notificationResponseDto=new NotificationResponseDto();
        try {
            Message  message = Message
                    .creator(new PhoneNumber(to)
                            , new PhoneNumber(twilioConfig.decodeValue(twilioConfig.getFromNumber()))
                            , sms)
                    .create();
            notificationResponseDto.setStatus(String.valueOf(Status.DELIVERED));
            //LOGGER.info("SMS Sent successfully....." + message.getStatus());
        }catch (Exception ex)
        {
            notificationResponseDto.setStatus(String.valueOf(Status.FAILED));
           // LOGGER.error("Kindly check if you mobile number is in verified account.....Error-->" + ex.getMessage());
        }
    return notificationResponseDto;
    }
}
