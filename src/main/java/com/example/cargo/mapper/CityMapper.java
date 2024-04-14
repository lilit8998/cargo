package com.example.cargo.mapper;

import com.example.cargo.dto.CityResponseDto;
import com.example.cargo.dto.SaveCityDto;
import com.example.cargo.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDate;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CityMapper {

    CityResponseDto map(City city);


    City map(SaveCityDto saveCityDto);

}
