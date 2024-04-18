package com.example.cargo.util;

import com.example.cargo.entity.City;
import com.example.cargo.entity.Country;
import com.example.cargo.repository.CityRepository;
import com.example.cargo.repository.CountryRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetCityAndCountry {

    @Value("${api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    public ResponseEntity<?> getAllCountries() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        String jsonString = responseEntity.getBody();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonString);
            JsonNode dataArray = rootNode.get("data");

            if (dataArray != null && dataArray.isArray()) {
                processCityAndCountryData(dataArray);
            }

            log.info("Data saved successfully");
            return ResponseEntity.ok("Data saved successfully");
        } catch (Exception e) {
            log.error("Error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    private void processCityAndCountryData(JsonNode dataArray) {
        for (JsonNode dataNode : dataArray) {
            String city = dataNode.get("city").asText();
            String country = dataNode.get("country").asText();

            log.info("Processing city: {} in country: {}", city, country);
            saveCityAndCountry(city, country);
        }
    }

    private void saveCityAndCountry(String city, String country) {
        Country existingCountry = countryRepository.findByName(country).orElse(null);
        if (existingCountry == null) {
            existingCountry = countryRepository.save(Country.builder().name(country).build());
            log.info("Saved new country: {}", existingCountry.getName());
        }
        cityRepository.save(City.builder()
                .name(city)
                .country(existingCountry)
                .build());
        log.info("Saved city: {} in country: {}", city, country);
    }
}