package com.sepal.notificationservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserContact extends BaseModel{

    private String email;
    private String mobile;

}
