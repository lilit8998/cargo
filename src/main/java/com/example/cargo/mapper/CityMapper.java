package com.example.cargo.mapper;

import com.example.cargo.dto.CityResponseDto;
import com.example.cargo.dto.SaveCityDto;
import com.example.cargo.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = CountryMapper.class)
public interface CityMapper {

    CityResponseDto cityToCityResponseDto(City city);
    List<CityResponseDto> cityListToCityResponseDtoList(List<City> cities);

    City map(SaveCityDto saveCityDto);

}