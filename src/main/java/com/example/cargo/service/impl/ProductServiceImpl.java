package com.example.cargo.service.impl;

import com.example.cargo.entity.Product;
import com.example.cargo.repository.ProductRepository;
import com.example.cargo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Product> findProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
       productRepository.deleteById(id);
    }

}
