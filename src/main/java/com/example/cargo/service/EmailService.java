package com.example.cargo.service;

import java.util.Map;

public interface EmailService {

    void sendEmail(String to, String subject, String body);

    boolean sendEmail(Map<String, String> emailDetails);
}
