package com.example.cargo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String details;

    @ManyToOne
    private User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;

    @OneToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @OneToOne(mappedBy = "product")
    private Orders orders;
}
