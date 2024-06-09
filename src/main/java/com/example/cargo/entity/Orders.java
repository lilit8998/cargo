package com.example.cargo.entity;

import com.example.cargo.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime sendDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deliveredDate;

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

    @ManyToOne
    private Branch branch;


}
