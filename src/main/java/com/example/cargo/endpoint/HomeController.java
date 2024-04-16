package com.example.cargo.endpoint;

import com.example.cargo.util.GetCitiesAndCountries;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private  final LocaleResolver localeResolver;
    private  final GetCitiesAndCountries getCitiesAndCountries;

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
    @GetMapping("/loginBranch")
    public String loginBranch() {
        return "loginBranch";
    }



    @GetMapping("/about")
    public String about() {
        return "services";
    }

    @GetMapping("/gallery")
    public String gallery() {
        return "gallery";
    }

    @GetMapping("/news")
    public String news() {
        return "news";
    }
    @GetMapping("/adminHome")
    public String adminHome() {
        return "/admin/adminHome";
    }

    @GetMapping("/countriesAndCities")
    public String getCountriesAndCities(){
        getCitiesAndCountries.getAllCountries();
     return "/admin/adminHome";
    }
}
