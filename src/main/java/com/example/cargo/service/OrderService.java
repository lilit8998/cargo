package com.example.cargo.service;

import com.example.cargo.dto.OrderResponseDto;
import com.example.cargo.dto.SaveOrderDto;
import com.example.cargo.entity.Orders;
import com.example.cargo.entity.Product;

import java.util.List;
import java.util.Optional;


public interface OrderService {

    OrderResponseDto save(SaveOrderDto ordersEntity);

    List<OrderResponseDto> getAll(Orders ordersEntity);

    Optional<Product> getByProduct(Product productEntity);

    OrderResponseDto findProductById(Long id);

    void deleteById(Long id);

    //List<Orders>findByBranch(BranchEntity branchEntity);
}
