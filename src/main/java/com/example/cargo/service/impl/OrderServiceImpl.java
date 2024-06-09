package com.example.cargo.service.impl;

import com.example.cargo.dto.OrderResponseDto;
import com.example.cargo.dto.SaveOrderDto;
import com.example.cargo.entity.Orders;
import com.example.cargo.entity.Product;
import com.example.cargo.mapper.OrderMapper;
import com.example.cargo.repository.OrderRepository;
import com.example.cargo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public OrderResponseDto save(SaveOrderDto saveOrderDto) {
        Orders orders = orderMapper.map(saveOrderDto);
        return orderMapper.map(orderRepository.save(orders)
        );
    }

    @Override
    public List<OrderResponseDto> getAll() {
        List<Orders> all = orderRepository.findAll();
        List<OrderResponseDto> orderResponseDto = new ArrayList<>();
        for (Orders orders : all) {
            orderResponseDto.add(orderMapper.map(orders));
        }
        return orderResponseDto;
    }



    public OrderResponseDto findOrdersById(Long id) {
        Orders orders = orderRepository.findById(id).orElse(null);
        if (orders == null) {
            return null;
        }
        return orderMapper.map(orders);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

}
