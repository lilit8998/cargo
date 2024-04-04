package com.example.cargo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageForAdminResponseDto {

    private long id;
    private String name;
    private String email;
    private String subject;
    private String text;
}
