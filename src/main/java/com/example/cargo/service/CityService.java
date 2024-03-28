package com.example.cargo.service;

import com.example.cargo.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityService {

    City save(City city);
    List<City> findAll();
    Optional<City> findCityById(int id);
    void deleteById(int id);
}
