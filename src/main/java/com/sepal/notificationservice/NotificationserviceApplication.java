package com.sepal.notificationservice;

import com.sepal.notificationservice.config.TwilioConfig;
import com.sepal.notificationservice.services.SendEmailService;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.EventListener;

import java.util.Base64;


@SpringBootApplication
@EnableConfigurationProperties

public class NotificationserviceApplication {

	@Autowired
	private TwilioConfig twilioConfig;

	@PostConstruct
	public void setup()
	{

		Twilio.init(decodeValue(twilioConfig.getAccountSID()),decodeValue(twilioConfig.getAuthToken()));

	}

	private String decodeValue(String encodedValue)
	{
		byte[] decodedBytes = Base64.getDecoder().decode(encodedValue);
        return new String(decodedBytes);
	}
	public static void main(String[] args) {
		SpringApplication.run(NotificationserviceApplication.class, args);
	}



}
