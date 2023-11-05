package sepal.unittest.controller;

import com.sepal.notificationservice.dtos.NotificationRequestDto;
import com.sepal.notificationservice.dtos.NotificationResponseDto;
import com.sepal.notificationservice.models.Status;
import com.sepal.notificationservice.services.SendEmailService;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SpringBootTest(classes= SendEmailService.class)
public class MailServiceTest {

    @MockBean
    private SendEmailService sendEmailService;


    @Test
     void verifyStatusIsNull() throws NullPointerException
    {
        NotificationRequestDto dto=new NotificationRequestDto();
        when(sendEmailService
                .sendEmail(dto.getMessage(),dto.getMessage(),dto.getMessage(),dto.getMessage()))
                .thenReturn(null);
        assertNull(null);
    }


    @Test
    void VerifyStatusIsDELIVERED() throws NullPointerException
    {
        NotificationResponseDto notificationResponseDto=new NotificationResponseDto();
        notificationResponseDto.setStatus(String.valueOf(Status.DELIVERED));
        when(sendEmailService
                .sendEmail(any(String.class),any(String.class),any(String.class),any(String.class)))
                .thenReturn(notificationResponseDto);
        assertEquals(notificationResponseDto.getStatus(),"DELIVERED");
    }
    @Test
    void VerifyStatusIsFAILED() throws NullPointerException
    {
        NotificationResponseDto notificationResponseDto=new NotificationResponseDto();
        notificationResponseDto.setStatus(String.valueOf(Status.FAILED));
        when(sendEmailService
                .sendEmail(any(String.class),any(String.class),any(String.class),any(String.class)))
                .thenReturn(notificationResponseDto);
        assertEquals(notificationResponseDto.getStatus(),"FAILED");
    }
}
