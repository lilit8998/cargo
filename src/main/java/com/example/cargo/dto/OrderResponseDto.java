package com.example.cargo.dto;

import com.example.cargo.entity.Product;
import com.example.cargo.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private long id;
    private LocalDate sendDate;
    private LocalDate deliveredDate;
    private OrderStatus orderStatus;
    private String orderLocation;
//    private Transport transportType; // because the entity doesn't exist now
 //   private User user; // it's too
  //  private Payment payment; // it's too
    private Product product;

}
