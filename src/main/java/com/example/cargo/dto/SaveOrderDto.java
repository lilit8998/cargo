package com.example.cargo.dto;

import com.example.cargo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveOrderDto {

    private LocalDate sendDate;
    private LocalDate deliveredDate;
    private double price;
    private String orderLocation;
//    private TransportationType transportationType; // I don't know will be is it correct here or not
   // private User user; // it's too
    private Product product;

}
