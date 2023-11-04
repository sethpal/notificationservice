package com.sepal.notificationservice.controllers;

import com.sepal.notificationservice.dtos.NotificationHistoryResponseDto;
import com.sepal.notificationservice.services.NotificationHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class NotificationHistoryController {

    NotificationHistoryService notificationHistoryService;

    public NotificationHistoryController(NotificationHistoryService notificationHistoryService) {
        this.notificationHistoryService = notificationHistoryService;
    }

    @GetMapping("/history")
    public List<NotificationHistoryResponseDto> getNotificationHistory()
    {
        return notificationHistoryService.getNotificationHistory();
    }
}
