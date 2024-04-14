package com.example.cargo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "branch")
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Country country;

    @OneToOne
    private City city;

    private String street;

    @ManyToOne
    private Orders orders;

    private String email;

    private String password;

}
