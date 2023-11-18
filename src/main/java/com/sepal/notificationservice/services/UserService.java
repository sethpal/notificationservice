package com.sepal.notificationservice.services;

import com.sepal.notificationservice.dtos.UserDto;
import com.sepal.notificationservice.thirdpartyClients.UserDtoClient;
import com.sepal.notificationservice.thirdpartyClients.UserServiceClient;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserServiceClient userServiceClient;
    public UserService(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    public UserDto getUserById(Long id)  {

        UserDto userDto =new UserDto();
        UserDtoClient userDtoClient=new UserDtoClient();
        userDtoClient=userServiceClient.getProductById(id);
        userDto.setUserid(id);
        userDto.setEmail(userDtoClient.getEmail());
        userDto.setMobile(userDtoClient.getPhone());
        return userDto;
    }


}
