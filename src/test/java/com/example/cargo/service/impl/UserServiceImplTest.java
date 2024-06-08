package com.example.cargo.service.impl;

import com.example.cargo.entity.User;
import com.example.cargo.repository.UserRepository;
import com.example.cargo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void findById() {
        long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(user);

        Optional<User> userFound = userService.findById(userId);
        assertTrue(userFound.isPresent());
        assertEquals(userId, userFound.get().getId());
    }

    @Test
    void findAll() {
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);

        List<User> usersFound = userService.findAll();

        assertEquals(users.size(), usersFound.size());
    }

    @Test
    void save() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        User saved = userService.save(user);

        assertEquals(user, saved);
        verify(userRepository,times(1)).save(user);
    }

    @Test
    void deleteById() {
        long userId = 1L;
        userService.deleteById(userId);
        verify(userRepository,times(1)).deleteById(userId);
    }

    @Test
    void findByEmail() {
        String email = "test@gmail.com";
        User user = new User();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(of(user));
        Optional<User> userFound = userService.findByEmail(email);
        assertTrue(userFound.isPresent());
        assertEquals(email, userFound.get().getEmail());

    }

    @Test
    void getMessage() {
        String email = "test@gmail.com";
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(of(user));
        String message = userService.getMessage(email);
        assertEquals(email, message);

    }

    @Test
    void isEmailExists() {
        String email = "test@gmail.com";
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(of(user));
        assertTrue(userService.isEmailExists(email));
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(1L);
        user.setName("newName");
        user.setSurname("newSurname");
        user.setEmail("testNew@gmail.com");
        user.setPassword("newPassword");
        when(userRepository.save(user)).thenReturn(user);

    }

    @Test
    void findByToken() {
        String token = "test";
        User user = new User();
        user.setToken(token);
        when(userRepository.findByToken(token)).thenReturn(of(user));
        User userFound = userService.findByToken(token);
        assertEquals(user, userFound);
    }

    @Test
    void register() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        user.setName("name");
        user.setSurname("surname");
        user.setDob(new Date());
        when(userRepository.save(user)).thenReturn(user);
    }
}