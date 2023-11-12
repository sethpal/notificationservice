package com.sepal.notificationservice.repositories;

import com.sepal.notificationservice.models.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserContactRepository extends JpaRepository<UserContact,Long> {


}
