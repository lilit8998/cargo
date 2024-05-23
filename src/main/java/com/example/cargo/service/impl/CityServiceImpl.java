package com.example.cargo.service.impl;

import com.example.cargo.dto.CityResponseDto;
import com.example.cargo.dto.SaveCityDto;
import com.example.cargo.entity.City;
import com.example.cargo.mapper.CityMapper;
import com.example.cargo.repository.CityRepository;
import com.example.cargo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    @Transactional
    public CityResponseDto save(SaveCityDto saveCityDto) {
        City city = cityMapper.map(saveCityDto);
        return cityMapper.cityToCityResponseDto(cityRepository.save(city));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityResponseDto> getAll() {
        return cityMapper.cityListToCityResponseDtoList(cityRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public CityResponseDto findCityById(int id) {
        City city = cityRepository.findById(id).orElse(null);
        if (city == null) {
            return null;
        }
        return cityMapper.cityToCityResponseDto(city);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        cityRepository.deleteById(id);
    }

    @Override
    public List<City> getCitiesByCountry(int countryId) {
        return cityRepository.findByCountry_Id(countryId);
    }

  
}
