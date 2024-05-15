package com.example.cargo.service.impl;

import com.example.cargo.dto.BranchSaveDto;
import com.example.cargo.entity.Branch;
import com.example.cargo.entity.City;
import com.example.cargo.entity.Country;
import com.example.cargo.mapper.BranchMapper;
import com.example.cargo.repository.BranchRepository;
import com.example.cargo.repository.CityRepository;
import com.example.cargo.repository.CountryRepository;
import com.example.cargo.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchServiceImp implements BranchService {
    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Override
    public void save(BranchSaveDto branchSaveDto,int city, int country) {
        Optional<City> findCityById = cityRepository.findById(city);
        Optional<Country> findCountryById = countryRepository.findById(country);
        if (findCityById.isPresent() && findCountryById.isPresent()) {
            Branch branch = branchMapper.branchSaveDtoToBranch(branchSaveDto);
            branch.setCity(findCityById.get());
            branch.setCountry(findCountryById.get());
            branchRepository.save(branch);
        }
    }


}
