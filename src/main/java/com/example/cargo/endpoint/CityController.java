package com.example.cargo.endpoint;

import com.example.cargo.entity.City;
import com.example.cargo.repository.CityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CityController {
    private final CityRepository cityRepository;

    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping("/city")
    public String city(Model model) {
        model.addAttribute("city",cityRepository.findAll() );
        return "calculate";
    }
}
