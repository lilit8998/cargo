package com.example.cargo.service;

import com.example.cargo.dto.UserDto;
import com.example.cargo.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    List<User> findAll();

    Optional<User> findByEmail(String email);

    User save(User user);

    void deleteById(Long id);

    String getMessage(String msg);

    boolean isEmailExists(String email);

    User updateUser(UserDto user) throws IOException;

    User findByToken(String token);

    User register(User user);
}
