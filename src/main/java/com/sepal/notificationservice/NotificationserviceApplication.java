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


@SpringBootApplication
@EnableConfigurationProperties

public class NotificationserviceApplication {

	@Autowired
	private TwilioConfig twilioConfig;

	@PostConstruct
	public void setup()
	{
		Twilio.init(twilioConfig.getAccountSID(),twilioConfig.getAuthToken());

	}

	public static void main(String[] args) {
		SpringApplication.run(NotificationserviceApplication.class, args);
	}



}
