package com.example.cargo.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name can't be empty")
    @Size(min = 3, max = 25, message = "Name size must be between 3 and 25")
    private String name;

    @NotBlank(message = "Surname can't be empty")
    @Size(min = 3, max = 30, message = "Surname size must be between 3 and 30")
    private String surname;

    private boolean isActive;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 16, message = "Password size must be between 6 and 16")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Password must contain at least 1 uppercase, 1 lowercase, 1 special character, and 1 digit")
    private String password;

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone can't be empty")
    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Invalid phone number format")
    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

}
