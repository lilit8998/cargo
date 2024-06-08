package com.example.cargo.endpoint;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Controller
public class HomeController {

    @Autowired
    private LocaleResolver localeResolver;

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        Locale currentLocale = localeResolver.resolveLocale(request);
        model.addAttribute("currentLang", currentLocale.getLanguage());
        return "index";
    }

    @GetMapping("/services")
    public String services() {
        return "services";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/gallery")
    public String gallery() {
        return "gallery";
    }

    @GetMapping("/news")
    public String news() {
        return "news";
    }
}
