package com.example.cargo.service.impl;

import com.example.cargo.dto.CountryResponseDto;
import com.example.cargo.dto.SaveCountryDto;
import com.example.cargo.entity.Country;
import com.example.cargo.mapper.CountryMapper;
import com.example.cargo.repository.CountryRepository;
import com.example.cargo.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public CountryResponseDto save(SaveCountryDto saveCountryDto) {
        Country country = countryMapper.map(saveCountryDto);
        return countryMapper.map(countryRepository.save(country));
    }

    @Override
    public List<CountryResponseDto> getAll() {
        List<Country> all = countryRepository.findAll();
        List<CountryResponseDto> countryResponseDto = new ArrayList<>();
        for (Country country : all) {
            countryResponseDto.add(countryMapper.map(country));
        }
        return countryResponseDto;
    }

    @Override
    public CountryResponseDto findCountryById(int id) {
        Country country = countryRepository.findById(id).orElse(null);
        if (country == null) {
            return null;
        }
        return countryMapper.map(country);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void deleteById(int id) {
        countryRepository.deleteById(id);
    }
}
