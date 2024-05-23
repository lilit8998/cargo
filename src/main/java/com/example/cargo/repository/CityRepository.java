package com.example.cargo.repository;

import com.example.cargo.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findByCountry_Id(int countryId);

}
