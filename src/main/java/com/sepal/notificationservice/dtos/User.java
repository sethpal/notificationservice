package com.sepal.notificationservice.dtos;

import lombok.Data;

@Data
public class User {
    private int id;
    private String firstName;
    private String lastName;
    String email;
    String mobile;

    String grevance;
    String userId;
}
