package com.sepal.notificationservice.thirdpartyClients;

import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDtoClient {

    private String name = "";
    private String email = "";
    private String phone = "";
    private String password="";
    private Set<Object> roles = new HashSet<>();
}
