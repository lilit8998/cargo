package com.example.cargo.service;

import com.example.cargo.dto.OrderResponseDto;
import com.example.cargo.dto.SaveOrderDto;
import com.example.cargo.entity.Product;

import java.util.List;


public interface OrderService {

    OrderResponseDto save(SaveOrderDto saveOrderDto);

    List<OrderResponseDto> getAll();


    OrderResponseDto findOrdersById(Long id);

    void deleteById(Long id);

}
