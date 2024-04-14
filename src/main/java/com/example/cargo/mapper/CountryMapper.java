package com.example.cargo.mapper;

import com.example.cargo.dto.CountryResponseDto;
import com.example.cargo.dto.SaveCountryDto;
import com.example.cargo.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDate;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CountryMapper {
    CountryResponseDto map(Country country);

    Country map(SaveCountryDto saveCountryDto);

}
