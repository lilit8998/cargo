package com.example.cargo.service.impl;

import com.example.cargo.entity.Size;
import com.example.cargo.repository.SizeEntityRepository;
import com.example.cargo.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeEntityRepository sizeEntityRepository;
    @Override
    public Size save(Size sizeEntity) {
        return sizeEntityRepository.save(sizeEntity);
    }

    @Override
    public List<Size> findAll(Size sizeEntity) {
        return sizeEntityRepository.findAll();
    }

    @Override
    public Size findById(int id) {
        return sizeEntityRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        sizeEntityRepository.deleteById(id);
    }


}
