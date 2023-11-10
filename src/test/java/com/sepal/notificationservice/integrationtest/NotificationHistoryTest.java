package com.sepal.notificationservice.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sepal.notificationservice.controllers.JsonMessageController;
import com.sepal.notificationservice.controllers.NotificationHistoryController;
import com.sepal.notificationservice.dtos.NotificationHistoryResponseDto;
import com.sepal.notificationservice.dtos.NotificationRequestDto;
import com.sepal.notificationservice.dtos.NotificationResponseDto;
import com.sepal.notificationservice.models.Status;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificationHistoryController.class)
public class NotificationHistoryTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NotificationHistoryController controller;

    @Test
    void VerifyNotificationStatus_ok_message() throws Exception {
        NotificationHistoryResponseDto message=new NotificationHistoryResponseDto();
        message.setStatus(String.valueOf(Status.DELIVERED));
        message.setNotificationDetails("Integration test");
        message.setNotificationId(1L);
        message.setUserId(1L);
        List<NotificationHistoryResponseDto> respnse=new ArrayList<NotificationHistoryResponseDto>();
        respnse.add(message);

        when(
                controller.getNotificationHistory()
        ).thenReturn(new ResponseEntity<>(respnse,HttpStatus.OK));

        ResultActions response=mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/history")
                        .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$.[0].status", is(message.getStatus())));
        response.andExpect(jsonPath("$.[0].notificationDetails", is(message.getNotificationDetails())));
    }
}
