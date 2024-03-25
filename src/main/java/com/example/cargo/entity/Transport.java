package com.example.cargo.entity;

import com.example.cargo.entity.enums.DriveTypes;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "transportation_type")
@Data

public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String from;
    private String to;
    private double price;
    private DriveTypes driveType;

    @OneToOne
    private Orders order;
}
