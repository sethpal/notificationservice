package com.sepal.notificationservice.services;

import com.sepal.notificationservice.dtos.NotificationHistoryResponseDto;
import com.sepal.notificationservice.models.Notification;
import com.sepal.notificationservice.repositories.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationHistoryService {
    private static final Logger LOGGER= LoggerFactory.getLogger(NotificationHistoryService.class);
    private final NotificationRepository notificationRepository;

    public NotificationHistoryService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    public List<NotificationHistoryResponseDto> getNotificationHistory() {

        List<Notification> allNotifications= notificationRepository.findAll();

        List<NotificationHistoryResponseDto> historyDtos=new ArrayList<>();

        for (Notification notification:allNotifications) {
            NotificationHistoryResponseDto notficationDto = new NotificationHistoryResponseDto();
            notficationDto.setNotificationId(notification.getId());
            notficationDto.setUserId(notification.getUser_id());
            notficationDto.setNotificationDetails(notification.getMessageDetails());
            notficationDto.setStatus(String.valueOf(notification.getStatus()));
            historyDtos.add(notficationDto);
        }
        return historyDtos;

    }


}
