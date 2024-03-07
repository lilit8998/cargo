package com.example.cargo.service;

import com.example.cargo.entity.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    List<Product> findAll();
    Product findProductById(int id);

    void deleteById(int id);

   // ProductEntity findProductByUser(Long id);

}
