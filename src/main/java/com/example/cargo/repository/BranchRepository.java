package com.example.cargo.repository;

import com.example.cargo.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch,Long> {
   Optional <Branch> findByEmail(String email);


   boolean existsByUser_Id(Long userId);



}


