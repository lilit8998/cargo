package com.example.cargo.service.impl;

import com.example.cargo.entity.Orders;
import com.example.cargo.entity.Product;
import com.example.cargo.repository.OrderRepository;
import com.example.cargo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public Orders save(Orders ordersEntity) {
        return orderRepository.save(ordersEntity);
    }

    @Override
    public List<Orders> findAll(Orders ordersEntity) {
        return orderRepository.findAll();
    }

    @Override
    public Orders findByProduct(Product productEntity) {
        return orderRepository.findByProduct(productEntity).orElse(null);
    }

    @Override
    public Orders findProductById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }

}
