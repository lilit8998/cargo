package com.example.cargo.repository;

import com.example.cargo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);

    User findById(Long id);

    void deleteById(Long id);




}
