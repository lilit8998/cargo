package com.example.cargo.service.impl;

import com.example.cargo.entity.ServiceOrder;
import com.example.cargo.repository.ServiceOrderRepository;
import com.example.cargo.service.OtherServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OtherServiceImpl implements OtherServiceService {
    private final ServiceOrderRepository serviceOrderRepository;
    @Override
    public List<ServiceOrder> findAll(ServiceOrder serviceOrderEntity) {
        return serviceOrderRepository.findAll();
    }

    @Override
    public Optional<ServiceOrder> findProductById(int id) {
        return serviceOrderRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        serviceOrderRepository.deleteById(id);
    }
}
