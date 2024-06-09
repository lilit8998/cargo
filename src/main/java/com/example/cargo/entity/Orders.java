package com.example.cargo.entity;

import com.example.cargo.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

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
    @JoinColumn(name = "transport_id", referencedColumnName = "id")
    private Transport transportId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @OneToOne
    private Payment payment;

    @ManyToOne
    private Branch branch;

    @Column(name = "city_from")
    private String cityFrom;

    @Column(name = "city_to")
    private String cityTo;

    @Column(name = "parcel_size")
    private String parcelSize;

    @Column(name = "lat1")
    private String lat1;

    @Column(name = "lon1")
    private String lon1;

    @Column(name = "lat2")
    private String lat2;

    @Column(name = "lon2")
    private String lon2;
}
