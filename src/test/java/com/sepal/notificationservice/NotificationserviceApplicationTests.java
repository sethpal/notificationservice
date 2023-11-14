package com.sepal.notificationservice;

import com.sun.mail.handlers.handler_base;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Base64;


@WebMvcTest(handler_base.class)
class NotificationserviceApplicationTests {

    @Test
    void contextLoads()
    {

    }

    @Test
    void encodeDecode()
    {
        String sample="ee0841de99efe03d93066ae3b1e086d2";
        String ecoded =encodeValue(sample);
        System.out.println("Encoded Value="+ ecoded);
        String decoded=decodeValue(ecoded);
        System.out.println("Decoded Value="+ decoded);


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
//    AC5527db0838d6a6ebc24206d9a2c85e4e
   // ee0841de99efe03d93066ae3b1e086d2
}
