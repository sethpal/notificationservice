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
    void encodeValue()
    {
        String token="+12568261234";
        String value=Base64.getEncoder().encodeToString(token.getBytes());
        System.out.println("Encoded Value->"+value);
    }

}
