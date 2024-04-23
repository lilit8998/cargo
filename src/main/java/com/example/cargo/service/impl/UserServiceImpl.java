package com.example.cargo.service.impl;

import com.example.cargo.dto.UserDto;
import com.example.cargo.entity.User;
import com.example.cargo.mapper.UserMapper;
import com.example.cargo.repository.UserRepository;
import com.example.cargo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<Object> findById(Long id) {
        return Optional.of(userRepository.findById(id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id.intValue());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String getMessage(String msg) {
        return (msg != null && !msg.isEmpty()) ? msg : null;
    }

    @Override
    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    @Transactional
    public User updateUser(UserDto userDto) {
        Long userId = userDto.getId();
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        User existingUser = userRepository.findById(userId);
        userMapper.updateUserFromDto(userDto, existingUser);
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            String encodedPassword = userDto.getPassword();
            existingUser.setPassword(encodedPassword);
        }
        return userRepository.save(existingUser);
    }
}