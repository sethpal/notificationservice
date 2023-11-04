package com.sepal.notificationservice.services;

import com.sepal.notificationservice.config.TwilioConfig;
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

    private static final Logger LOGGER= LoggerFactory.getLogger(SendSMSService.class);

    public void sendSMS(String to, String sms) {
        try {
            Message  message = Message
                    .creator(new PhoneNumber(to)
                            , new PhoneNumber(twilioConfig.getFromNumber())
                            , sms)
                    .create();

            LOGGER.info("SMS Sent successfully....." + message.getStatus());
        }catch (Exception ex)
        {
            LOGGER.error("Kindly check if you mobile number is in verified account.....Error-->" + ex.getMessage());
        }

    }
}
