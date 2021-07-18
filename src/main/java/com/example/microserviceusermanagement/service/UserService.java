package com.example.microserviceusermanagement.service;

import com.example.microserviceusermanagement.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findByUsername(String username);

    List<String> findUsers(List<Long> idList);

    void delete(Long id);
}
