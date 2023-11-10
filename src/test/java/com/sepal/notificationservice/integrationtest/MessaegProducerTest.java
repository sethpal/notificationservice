package com.sepal.notificationservice.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sepal.notificationservice.controllers.JsonMessageController;
import com.sepal.notificationservice.dtos.NotificationRequestDto;
import com.sepal.notificationservice.dtos.NotificationResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;


@WebMvcTest(JsonMessageController.class)
public class MessaegProducerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JsonMessageController controller;


    @Test
    void VerifyNotificationStatus_ok_message() throws Exception {
        NotificationRequestDto notificationRequestDto=new NotificationRequestDto();
        notificationRequestDto.setUserid(1L);
        notificationRequestDto.setMessage("Integration test");
        NotificationResponseDto message=new NotificationResponseDto();
        message.setStatus("Notificaiton Sent");


        when(
                controller.sendJsonMessage(notificationRequestDto)
        ).thenReturn(new ResponseEntity<>(message,HttpStatus.OK));
        ResultActions response=mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(notificationRequestDto)));
        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$.status", is(message.getStatus())));
    }

    @Test
    void VerifyResponseStatusWhenPayloadIsIncorrect() throws Exception {
        NotificationRequestDto notificationRequestDto=new NotificationRequestDto();
        notificationRequestDto.setUserid(1L);

        NotificationResponseDto message=new NotificationResponseDto();
        message.setStatus("400");

        when(
                controller.sendJsonMessage(notificationRequestDto)
        ).thenReturn(new ResponseEntity<>(message,HttpStatus.BAD_REQUEST));

        ResultActions response=mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(notificationRequestDto)));

        response.andExpect(jsonPath("$.status", is(message.getStatus())));


    }
}
