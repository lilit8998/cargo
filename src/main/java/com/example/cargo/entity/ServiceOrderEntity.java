package com.example.cargo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "services_order")
@Data
public class ServiceOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private double price;
    @OneToOne
    @JoinColumn(name = "id")
    private OrdersEntity orderId;
}
