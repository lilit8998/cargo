package com.example.cargo.service;

import com.example.cargo.dto.OrderResponseDto;
import com.example.cargo.dto.SaveOrderDto;

import java.util.List;


public interface OrderService {

    OrderResponseDto save(SaveOrderDto ordersEntity);

    List<OrderResponseDto> getAll();


    OrderResponseDto findOrdersById(Long id);

    void deleteById(Long id);

}
