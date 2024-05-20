package com.example.cargo.entity;


import com.example.cargo.entity.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "password")
    private String password;


    @Column(name = "email")
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "dob")
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

    private String token;
}
