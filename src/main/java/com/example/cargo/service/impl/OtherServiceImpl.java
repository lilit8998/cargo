package com.example.cargo.service.impl;

import com.example.cargo.entity.ServiceOrder;
import com.example.cargo.repository.ServiceOrderRepository;
import com.example.cargo.service.OtherServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public Optional<ServiceOrder> findProductById(int id) {
        return serviceOrderRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public void deleteById(int id) {
        serviceOrderRepository.deleteById(id);
    }
}
