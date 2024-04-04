package com.example.cargo.dto;

import com.example.cargo.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveCityDto {

    private String name;
    private Country country;
}
