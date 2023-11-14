package com.sepal.notificationservice;


import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sepal.notificationservice.config.TwilioConfig;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@SpringBootApplication
@Configuration
public class NotificationserviceApplication {

	@Value("${twilio.accountSID}")
	private String accountSSID;

	@Value("${twilio.authToken}")
	private String auth;

	public static void main(String[] args) {
		SpringApplication.run(NotificationserviceApplication.class, args);
	}

	@PostConstruct
	public void setup()
	{
		Twilio.init(decodeValue(accountSSID),decodeValue(auth));
	}
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
