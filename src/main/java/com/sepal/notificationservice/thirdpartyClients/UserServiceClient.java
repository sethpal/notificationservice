package com.sepal.notificationservice.thirdpartyClients;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceClient {

    private RestTemplateBuilder restTemplateBuilder;
    @Value("${user.api.url}")
    private String userApiUrl;

    @Value("${user.api.path}")
    private String userApiPath;
    private String userAPIBasePath;
    private String specificUserPath;

    public UserServiceClient(RestTemplateBuilder restTemplateBuilder,
                                         @Value("${user.api.url}") String userApiUrl,
                                         @Value("${user.api.path}") String userApiPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.userAPIBasePath=userApiUrl+userApiPath;
        this.specificUserPath  = userApiUrl+userApiPath +  "/{id}";
    }

    public UserDtoClient getProductById(Long id)  {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<UserDtoClient> response =
                restTemplate.getForEntity(specificUserPath, UserDtoClient.class, id);

        UserDtoClient userDtoClient = response.getBody();

        if (userDtoClient == null) {

            return new UserDtoClient();
        }
        return userDtoClient;
    }

}
