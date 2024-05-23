package com.example.cargo.endpoint;

import com.example.cargo.entity.City;
import com.example.cargo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AjaxController {

    private final CityService cityService;

    @GetMapping("/cities")
    public List<City> getCities(@RequestParam("countryId") int countryId) {
        return cityService.getCitiesByCountry(countryId);
    }
}

