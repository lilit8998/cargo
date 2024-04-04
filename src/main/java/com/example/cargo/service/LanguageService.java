package com.example.cargo.service;

import jakarta.servlet.http.HttpServletRequest;

public interface LanguageService {
    void changeLanguage(String lang, HttpServletRequest request);
}
