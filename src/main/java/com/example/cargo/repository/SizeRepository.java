package com.example.cargo.repository;

import com.example.cargo.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size,Long> {
    Optional<Size> findByWidthAndHeightAndDepthAndWeight(int width, int height, int depth, int weight);
}
