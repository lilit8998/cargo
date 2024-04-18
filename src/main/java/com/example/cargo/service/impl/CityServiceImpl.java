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
        return cityMapper.map(cityRepository.save(city));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityResponseDto> getAll() {
        List<City> all = cityRepository.findAll();
        List<CityResponseDto> cityResponseDto = new ArrayList<>();
        for (City city : all) {
            cityResponseDto.add(cityMapper.map(city));
        }
        return cityResponseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public CityResponseDto findCityById(int id) {
        City city = cityRepository.findById(id).orElse(null);
        if (city == null) {
            return null;
        }
        return cityMapper.map(city);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        cityRepository.deleteById(id);
    }

}
