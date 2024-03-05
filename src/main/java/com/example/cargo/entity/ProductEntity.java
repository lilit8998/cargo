package com.example.cargo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "product")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String details;
    private long receivedId;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate orderDate;

    @OneToOne
    @JoinColumn(name = "size_id")
    private SizeEntity sizeId;
}
