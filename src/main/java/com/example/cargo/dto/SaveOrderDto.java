package com.example.cargo.dto;

import com.example.cargo.entity.Product;
import com.example.cargo.entity.Transport;
import com.example.cargo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveOrderDto {

    private LocalDateTime sendDate;
    private LocalDateTime deliveredDate;
    private double price;
    private String orderLocation;
    private Transport transportationType;
    private User user;
    private Product product;

}
