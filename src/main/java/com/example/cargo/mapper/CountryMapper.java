package com.example.cargo.mapper;

import com.example.cargo.dto.CountryResponseDto;
import com.example.cargo.dto.SaveCountryDto;
import com.example.cargo.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CountryMapper {
    CountryResponseDto countryToCountryResponseDto(Country country);
    List<CountryResponseDto> countryListToCountryResponseDtoList(List<Country> countries);

    Country countryResponseDtoToCountry(CountryResponseDto countryResponseDto);

    Country map(SaveCountryDto saveCountryDto);

}
