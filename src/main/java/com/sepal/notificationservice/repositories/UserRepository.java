package com.sepal.notificationservice.repositories;

import com.sepal.notificationservice.models.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserContact,Long> {


}
