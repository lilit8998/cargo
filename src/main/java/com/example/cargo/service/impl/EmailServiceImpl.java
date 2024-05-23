package com.example.cargo.service.impl;

import com.example.cargo.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @Override
    public boolean sendEmail(Map<String, String> emailDetails) {
        try {
            String name = emailDetails.get("name");
            String email = emailDetails.get("email");
            String message = emailDetails.get("message");

            String subject = "New Message from Contact Form";
            String text = "Name: " + name + "\nEmail: " + email + "\nMessage: " + message;

            sendEmail("cargo9088@gmail.com", subject, text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
