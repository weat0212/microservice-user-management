package com.example.microserviceusermanagement.repository;

import com.example.microserviceusermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u.name FROM User u WHERE u.id IN (:pIdList)")
    List<String> findByIdList(@Param("pIdList") List<Long> idList);
}
