package com.example.cargo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;

import java.time.LocalDate;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String details;

    @ManyToOne
    private User createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate orderDate;

    @OneToOne
    @JoinColumn(name = "size_id")
    private Size sizeId;
}
