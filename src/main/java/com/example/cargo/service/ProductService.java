package com.example.cargo.service;

import com.example.cargo.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product product);
    List<Product> findAll();
   Optional<Product> findProductById(int id);

    void deleteById(int id);

   // ProductEntity findProductByUser(Long id);

}
