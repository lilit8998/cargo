package com.example.cargo.service;

import com.example.cargo.entity.Size;

import java.util.List;


public interface SizeService {
    Size save(Size sizeEntity);
    List<Size> findAll(Size sizeEntity);
    Size findById(int id);

    void deleteById(int id);

}
