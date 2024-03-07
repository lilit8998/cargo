package com.example.cargo.service;

import com.example.cargo.entity.Orders;
import com.example.cargo.entity.Product;

import java.util.List;


public interface OrderService {

    Orders save(Orders ordersEntity);

    List<Orders> findAll(Orders ordersEntity);

    Orders findByProduct(Product productEntity);

    Orders findProductById(int id);

    void deleteById(int id);

    //List<OrdersEntity>findByBranch(BranchEntity branchEntity);
}
