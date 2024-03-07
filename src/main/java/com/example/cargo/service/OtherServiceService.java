package com.example.cargo.service;

import com.example.cargo.entity.ServiceOrder;

import java.util.List;


public interface OtherServiceService {
    List<ServiceOrder> findAll(ServiceOrder serviceOrderEntity);

    ServiceOrder findProductById(int id);

    void deleteById(int id);
}
