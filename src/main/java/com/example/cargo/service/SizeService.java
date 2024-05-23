package com.example.cargo.service;

import com.example.cargo.entity.Size;

import java.util.List;
import java.util.Optional;


public interface SizeService {
    Size save(Size size);
    Optional<Size> findById(Long id);

    void deleteById(Long id);

}
