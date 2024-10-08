package com.example.cargo.repository;

import com.example.cargo.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {

   Optional <Country> findByName(String country);

}
