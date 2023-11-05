package com.sepal.notificationservice.services;

import com.sepal.notificationservice.dtos.NotificationResponseDto;
import com.sepal.notificationservice.models.Status;
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

    public NotificationResponseDto sendEmail(
            String fromMail,
            String toEmail,
            String subject,
            String body)
    {
        NotificationResponseDto notificationResponseDto=new NotificationResponseDto();
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromMail);
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(subject);
            mailSender.send(message);
            LOGGER.info("Mail Sent Successfully........."+mailSender);
            notificationResponseDto.setStatus(String.valueOf(Status.DELIVERED));
        }catch(Exception ex)
        {
            LOGGER.error("Error -->" + ex.getMessage());
            notificationResponseDto.setStatus(String.valueOf(Status.FAILED));
        }
        return notificationResponseDto;
    }

    public String sendMail()
    {
        return "SENT";
    }

}
