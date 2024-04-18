package com.example.cargo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.bind.annotation.InitBinder;

@Entity
@Table(name = "branch")
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Country is required")
    @OneToOne
    private Country country;

    @NotNull(message = "City is required")
    @OneToOne
    private City city;

    @NotBlank(message = "Street is required")
    private String street;

    @ManyToOne
    private Orders orders;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

}
