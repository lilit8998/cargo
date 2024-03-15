package com.example.cargo.entity;

import com.example.cargo.entity.enums.UserRole;
import jakarta.persistence.*;
import jdk.jfr.Relational;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String surname;

    private boolean isActive;

    private String password;

    private String email;

    private String phoneNumber;

    private Date dob;

    @ManyToOne
    private ProductEntity productId;

    @OneToOne
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;


}
