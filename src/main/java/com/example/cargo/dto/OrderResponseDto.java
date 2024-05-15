package com.example.cargo.dto;

import com.example.cargo.entity.Payment;
import com.example.cargo.entity.Product;
import com.example.cargo.entity.Transport;
import com.example.cargo.entity.User;
import com.example.cargo.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private long id;
    private LocalDateTime sendDate;
    private LocalDateTime deliveredDate;
    private OrderStatus orderStatus;
    private String orderLocation;
    private Transport transportType;
    private User user;
    private Payment payment;
    private Product product;

}
