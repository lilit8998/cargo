package com.example.cargo.service;

import com.example.cargo.entity.ServiceOrder;

import java.util.List;
import java.util.Optional;


public interface OtherServiceService {
    List<ServiceOrder> findAll(ServiceOrder serviceOrderEntity);

    Optional<ServiceOrder> findProductById(int id);

    void deleteById(int id);
}
