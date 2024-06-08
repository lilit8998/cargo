package com.example.cargo.service.impl;

import com.example.cargo.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailServiceImplTest {

    @Mock
    private JavaMailSender mailSender;
    @InjectMocks
    private EmailService emailService = new EmailServiceImpl(mailSender);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendEmail() {
        Map<String, String> emailDetails = new HashMap<>();
        emailDetails.put("name", "John Doe");
        emailDetails.put("email", "johndoe@example.com");
        emailDetails.put("message", "Test Message");

        boolean result = emailService.sendEmail(emailDetails);
        assertFalse(result);
    }

}