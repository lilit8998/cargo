package com.example.cargo.endpoint;

import com.example.cargo.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

public class EmailController {
    private final EmailService emailService;
    private static final Logger log = LoggerFactory.getLogger(EmailController.class);

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/email-send")
    public Map<String, Boolean> sendEmail(@RequestBody Map<String, String> request) {
        Map<String, Boolean> response = new HashMap<>();
        try {
            log.info("Name: {}", request.get("name"));
            log.info("Email: {}", request.get("email"));
            log.info("Message: {}", request.get("message"));

            boolean success = emailService.sendEmail(request);
            response.put("success", success);
        } catch (Exception e) {
            log.error("Error occurred while sending email", e);
            response.put("success", false);
        }
        return response;
    }
}
