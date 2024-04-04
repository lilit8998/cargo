package com.example.cargo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveMessageForAdminDto {

    private String name;
    private String email;
    private String subject;
    private String text;
}
