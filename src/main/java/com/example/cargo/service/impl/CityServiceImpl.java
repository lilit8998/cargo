package com.example.cargo.service.impl;

import com.example.cargo.entity.City;
import com.example.cargo.repository.CityRepository;
import com.example.cargo.service.CityService;

import java.util.Optional;

public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }
}
