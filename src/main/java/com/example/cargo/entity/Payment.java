package com.example.cargo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payment")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String cardNumber;
    private String cvc;

    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "orders", referencedColumnName = "id")
    private Orders orders;
}
