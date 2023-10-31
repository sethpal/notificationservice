package com.sepal.notificationservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    private static final Logger LOGGER= LoggerFactory.getLogger(SendEmailService.class);

    private final JavaMailSender mailSender;

    public SendEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(
            String toEmail,
            String subject,
            String body)
    {
        SimpleMailMessage message =new SimpleMailMessage();
        message.setFrom("tpulse385@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        LOGGER.info("Mail Sent Successfully.........");
    }


}
