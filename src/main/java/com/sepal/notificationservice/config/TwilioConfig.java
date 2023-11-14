package com.sepal.notificationservice.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data
public class TwilioConfig {

    private String accountSID;

    private String authToken;

    private String fromNumber;
    private String toNumber="+919538965414";

    public String decodeValue(String encodedValue)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedValue);
        return new String(decodedBytes);
    }

    public String  encodeValue(String encodedValue) {
        String BasicBase64format
                = Base64.getEncoder()
                .encodeToString(encodedValue.getBytes());
        return BasicBase64format;
    }


}
