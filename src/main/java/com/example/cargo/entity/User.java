package com.example.cargo.entity;

import com.example.cargo.entity.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name can't be empty")
    @Size(min = 3, max = 25,message = "Name size must be 3 between 25")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "Surname can't be empty")
    @Size(min = 3, max = 30,message = "Surname size must be 3 between 25")
    private String surname;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "password")
    @NotEmpty(message = "Password can't be empty")
    @Size(min = 6, max = 16,message = "Password size must be 6 between 16")
    @Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){4,12}$",
            message = "password must contain at least 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    private String password;

    @Column(name = "email")
    @NotEmpty(message = "Email can't be empty")
    @Email(message = "Invalid email format")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email format")
    private String email;

    @Column(name = "phone", nullable = false)
    @NotEmpty(message = "Phone can't be empty")
    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Invalid phone number format")
    private String phone;

    @Column(name = "dob")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment paymentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole = UserRole.USER;


}
