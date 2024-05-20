package com.example.cargo.endpoint;

import com.example.cargo.dto.OrderResponseDto;
import com.example.cargo.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> getAll() {
        return orderService.getAll();
    }
}

