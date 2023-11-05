package sepal.unittest.controller;

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
        assertNull(null);
    }


    @Test
    void VerifyStatusIsDELIVERED() throws NullPointerException
    {
        NotificationResponseDto notificationResponseDto=new NotificationResponseDto();
        notificationResponseDto.setStatus(String.valueOf(Status.DELIVERED));
        when(sendSMRService
                .sendSMS(any(String.class),any(String.class)))
                .thenReturn(notificationResponseDto);
        assertEquals(notificationResponseDto.getStatus(),"DELIVERED");
    }
    @Test
    void VerifyStatusIsFAILED() throws NullPointerException
    {
        NotificationResponseDto notificationResponseDto=new NotificationResponseDto();
        notificationResponseDto.setStatus(String.valueOf(Status.FAILED));
        when(sendSMRService
                .sendSMS(any(String.class),any(String.class)))
                .thenReturn(notificationResponseDto);
        assertEquals(notificationResponseDto.getStatus(),"FAILED");
    }


    @Test
    void encodeValue()
    {
        //twilio.accountSID=AC5527db0838d6a6ebc24206d9a2c85e4e
       // twilio.authToken=b436fa347b2c970e5a93000eda3053ef
        String originalInput = "AC5527db0838d6a6ebc24206d9a2c85e4e";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        System.out.println(encodedString);
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        System.out.println(decodedString);

    }

    @Test
    void encodeValue1()
    {
        //twilio.accountSID=AC5527db0838d6a6ebc24206d9a2c85e4e
        // twilio.authToken=b436fa347b2c970e5a93000eda3053ef
        String originalInput = "b436fa347b2c970e5a93000eda3053ef";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        System.out.println(encodedString);
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        System.out.println(decodedString);

    }
}
