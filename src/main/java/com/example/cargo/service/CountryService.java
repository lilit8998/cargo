package com.example.cargo.service;

import com.example.cargo.dto.CountryResponseDto;
import com.example.cargo.dto.SaveCountryDto;

import java.util.List;

public interface CountryService {
    CountryResponseDto save (SaveCountryDto country);
    List<CountryResponseDto> getAll();
    CountryResponseDto findCountryById(int id);
    void deleteById(int id);

}
