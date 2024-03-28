package com.example.cargo.service;

import com.example.cargo.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Country save (Country country);
    List<Country> findAll();
    Optional<Country> findCountryById(int id);
    void deleteById(int id);

}
