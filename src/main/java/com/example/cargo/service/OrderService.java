package com.example.cargo.service;

import com.example.cargo.dto.OrderResponseDto;
import com.example.cargo.dto.SaveOrderDto;
import com.example.cargo.entity.Product;

import java.util.List;


public interface OrderService {

    OrderResponseDto save(SaveOrderDto ordersEntity);

    List<OrderResponseDto> getAll();

    Product getByProduct(Long productId);

    OrderResponseDto findOrdersById(Long id);

    void deleteById(Long id);

    //List<Orders>findByBranch(BranchEntity branchEntity);
}
