package com.sepal.notificationservice.dtos;

import com.sepal.notificationservice.models.ContactType;
import com.sepal.notificationservice.models.Notification;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class UserDto {
    Long userid;
    String email;
    String mobile;
}
