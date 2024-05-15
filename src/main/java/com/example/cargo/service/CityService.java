package com.example.cargo.service;

import com.example.cargo.dto.CityResponseDto;
import com.example.cargo.dto.SaveCityDto;
import com.example.cargo.entity.City;

import java.util.List;

public interface CityService {
    CityResponseDto save(SaveCityDto saveCityDto);

    List<CityResponseDto> getAll();
    CityResponseDto findCityById(int id);
    void deleteById(int id);
    List<City> getCitiesByCountry(int countryId);

}
