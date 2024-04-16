package com.example.cargo.repository;

import com.example.cargo.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    Country findByName(String country);

}
