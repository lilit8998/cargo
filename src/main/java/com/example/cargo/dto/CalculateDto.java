package com.example.cargo.dto;

import com.example.cargo.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalculateDto {
    private String cityFrom;
    private String cityTo;
    private String parcelSize;
    private double price;

    public String getCityFrom() {
        return cityFrom != null ? cityFrom : " " ;
    }
    public String getCityTo() {
        return cityTo != null ? cityTo : " " ;
    }
    public String getParcelSize() {
        return parcelSize != null ? parcelSize : " " ;
    }
}
