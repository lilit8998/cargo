package com.example.cargo.endpoint;

import com.example.cargo.service.LanguageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LanguageController {
    private final LanguageService languageService;
    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/change-language")
    public String changeLanguage(HttpServletRequest request) {
        String lang = request.getParameter("lang");
        languageService.changeLanguage(lang, request);
        return "redirect:/";
    }
}
