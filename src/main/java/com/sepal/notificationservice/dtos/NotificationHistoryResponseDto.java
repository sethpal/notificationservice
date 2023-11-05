package com.sepal.notificationservice.dtos;

import lombok.Data;

@Data
public class NotificationHistoryResponseDto {
    private Long notificationId;
    private Long userId;
    private String notificationDetails;
    private String status;


}
