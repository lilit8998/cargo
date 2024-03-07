package com.example.cargo.repository;

import com.example.cargo.entity.ServiceOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrderEntity,Integer> {
}
