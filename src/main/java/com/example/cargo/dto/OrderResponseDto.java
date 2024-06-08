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

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private long id;
    private String cityFrom;
    private String cityTo;
    private String parcelSize;
    private double price;

}
