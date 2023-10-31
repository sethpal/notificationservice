package com.sepal.notificationservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel{

    String firstName;
    String lastName;
   @OneToOne
    Notification notification;
   ContactType channelType;
}
