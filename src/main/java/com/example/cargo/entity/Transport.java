package com.example.cargo.entity;

import com.example.cargo.entity.enums.DriveTypes;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transportation_type")
@Data

public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDate;
    private String from;
    private String to;
    private double price;
    private DriveTypes driveType;

    @OneToOne
    private Orders order;
}
