package com.example.cargo.service.impl;

import com.example.cargo.entity.Product;
import com.example.cargo.repository.ProductRepository;
import com.example.cargo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public void deleteById(int id) {
       productRepository.deleteById(id);
    }

    @Override
    public List<Product> getReceivedProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getReleasedProducts() {
        return productRepository.findAll();
    }

}
