package com.example.cargo.endpoint;

import com.example.cargo.util.CitiesAndCountries;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final LocaleResolver localeResolver;
    private final CitiesAndCountries citiesAndCountries;

    @Value("${picture.upload.directory}")
    private String uploadDirectory;

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        Locale currentLocale = localeResolver.resolveLocale(request);
        model.addAttribute("currentLang", currentLocale.getLanguage());
        return "index";
    }


    @GetMapping(value = "/getImage",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("picName") String picName) throws IOException {
        File file = new File(uploadDirectory, picName);
        if (file.exists()) {
            return IOUtils.toByteArray(new FileInputStream(file));
        }
        return null;
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


    @GetMapping("/adminHome")
    public String adminHome() {
        return "/admin/adminHome";

    }

    @GetMapping("/countriesAndCities")
    public String getCountriesAndCities() {
        citiesAndCountries.getAllCountries();
        return "/admin/adminHome";
    }
}