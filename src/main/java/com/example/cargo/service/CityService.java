package com.example.cargo.service;

import com.example.cargo.entity.City;
import com.example.cargo.entity.Size;

import java.util.List;
import java.util.Optional;

public interface CityService {
    Optional<City> findById(Long id);


}
