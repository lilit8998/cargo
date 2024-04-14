package com.example.cargo.repository;

import com.example.cargo.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch,Long> {
    Branch findByEmail(String email);
}


