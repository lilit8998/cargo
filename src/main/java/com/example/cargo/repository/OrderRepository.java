package com.example.cargo.repository;

import com.example.cargo.entity.Orders;
import com.example.cargo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {
    Optional<Orders> findByProduct(Product productEntity);
}
