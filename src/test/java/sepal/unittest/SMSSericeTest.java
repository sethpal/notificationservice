package sepal.unittest;

import com.sepal.notificationservice.dtos.NotificationRequestDto;
import com.sepal.notificationservice.dtos.NotificationResponseDto;
import com.sepal.notificationservice.models.Status;
import com.sepal.notificationservice.services.SendSMSService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Base64;

@SpringBootTest(classes= SendSMSService.class)
public class SMSSericeTest {

    @MockBean
    private SendSMSService sendSMRService;


    @Test
    void verifyStatusIsNull() throws NullPointerException
    {
        NotificationRequestDto dto=new NotificationRequestDto();
        when(sendSMRService
                .sendSMS(dto.getMessage(),dto.getMessage()))
                .thenReturn(null);
        assertNull(sendSMRService
                .sendSMS(dto.getMessage(),dto.getMessage()));
    }


    @Test
    void VerifyStatusIsDELIVERED() throws NullPointerException
    {
        NotificationResponseDto notificationResponseDto=new NotificationResponseDto();
        notificationResponseDto.setStatus(String.valueOf(Status.DELIVERED));
        when(sendSMRService
                .sendSMS(any(String.class),any(String.class)))
                .thenReturn(notificationResponseDto);
        assertEquals(notificationResponseDto.getStatus(),sendSMRService
                .sendSMS("+39656756735", "Test delivered SMS Notificaiton ").getStatus());
    }
    @Test
    void VerifyStatusIsFAILED() throws NullPointerException
    {
        NotificationResponseDto notificationResponseDto=new NotificationResponseDto();
        notificationResponseDto.setStatus(String.valueOf(Status.FAILED));
        when(sendSMRService
                .sendSMS(any(String.class),any(String.class)))
                .thenReturn(notificationResponseDto);
        assertEquals(notificationResponseDto.getStatus(),sendSMRService
                .sendSMS("393656756735", "Test failed SMS Notificaiton ").getStatus());
    }

}
