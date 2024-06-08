package com.example.cargo.service;

import com.example.cargo.dto.OrderResponseDto;
import com.example.cargo.dto.SaveOrderDto;
import com.example.cargo.entity.Orders;

import java.util.List;


public interface OrderService {

    OrderResponseDto save(SaveOrderDto saveOrderDto);

    List<OrderResponseDto> getAll();

    Orders getByProduct(Long productId);

    OrderResponseDto findOrdersById(Long id);

    void deleteById(Long id);

}
