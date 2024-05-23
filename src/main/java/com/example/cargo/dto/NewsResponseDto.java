package com.example.cargo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsResponseDto {

    private long id;
    private String title;
    private String description;
    private LocalDate publishDate;
    private String picName;
}
