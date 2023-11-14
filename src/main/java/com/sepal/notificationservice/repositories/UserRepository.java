package com.sepal.notificationservice.repositories;

import com.sepal.notificationservice.models.UserContact;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<UserContact,Long> {


}
