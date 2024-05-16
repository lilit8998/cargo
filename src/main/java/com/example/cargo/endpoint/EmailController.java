package com.example.cargo.endpoint;

import com.example.cargo.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class EmailController {
    private final EmailService emailService;
    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/email-send")
    public Map<String, Boolean> sendEmail(@RequestBody Map<String, String> request) {
        Map<String, Boolean> response = new HashMap<>();
        try {
            String name = request.get("name");
            String email = request.get("email");
            String message = request.get("message");

            logger.info("Name: {}", name);
            logger.info("Email: {}", email);
            logger.info("Message: {}", message);

            String subject = "New Message from Contact Form";
            String text = "Name: " + name + "\nEmail: " + email + "\nMessage: " + message;

            emailService.sendEmail("cargo9088@gmail.com", subject, text);
            response.put("success", true);
        } catch (Exception e) {
            logger.error("Error occurred while sending email", e);
            response.put("success", false);
        }
        return response;
    }
}
