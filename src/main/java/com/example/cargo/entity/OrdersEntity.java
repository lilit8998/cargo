package com.example.cargo.entity;

import com.example.cargo.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate sendDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveredDate;

    private double price;

    private OrderStatus orderStatus;
    private String orderLocation;
    @OneToOne
    @JoinColumn(name = "id")
    private TransportEntity transportationType;
    @ManyToOne
    @JoinColumn(name = "id")
    private UserEntity user;
    @OneToOne
    private PaymentEntity paymentID;
    @OneToOne
    @JoinColumn(name = "id")
    private ProductEntity product;


}
