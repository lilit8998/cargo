package com.example.cargo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveOrderDto {
    private String cityFrom;
    private String cityTo;
    private String parcelSize;
    private double price;

}
