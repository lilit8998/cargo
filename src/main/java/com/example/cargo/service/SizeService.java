package com.example.cargo.service;

import com.example.cargo.entity.Size;

import java.util.List;
import java.util.Optional;


public interface SizeService {
    Size save(Size sizeEntity);
    Optional<Size> findById(int id);

    void deleteById(int id);

}
