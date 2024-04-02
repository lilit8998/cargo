package com.example.cargo.service.impl;

import com.example.cargo.service.LanguageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Locale;

@Service
public class LanguageServiceImpl implements LanguageService {
    @Override
    public void changeLanguage(String lang, HttpServletRequest request) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver != null) {
            localeResolver.setLocale(request, null, new Locale(lang));
        }
    }
}
