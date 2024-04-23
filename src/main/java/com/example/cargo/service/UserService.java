package com.example.cargo.service;

import com.example.cargo.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<Object> findById(Long id);

    List<User> findAll();

    Optional<User> findByEmail(String email);

    void save(User user);

    void deleteById(Long id);

    String getMessage(String msg);

    boolean isEmailExists(String email);

    User updateUser(User user) throws IOException;
}
