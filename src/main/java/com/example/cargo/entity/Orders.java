package com.example.cargo.entity;

import com.example.cargo.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate sendDate;

//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate deliveredDate;

    private double price;

    private OrderStatus orderStatus;
    private String orderLocation;
    @OneToOne
    @JoinColumn(name = "transportId", referencedColumnName = "id")
    private Transport transportId;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;
    @OneToOne
    private Payment payment;

    @OneToOne
    @JoinColumn(name = "product_id" , referencedColumnName = "id")
    private Product product;




}
