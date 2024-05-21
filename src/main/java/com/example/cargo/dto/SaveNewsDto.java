package com.example.cargo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveNewsDto {

    private String title;
    private String description;
    private LocalDate publishDate;
    private String picName;

}
