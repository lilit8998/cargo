package com.example.cargo.service.impl;

import com.example.cargo.entity.Size;
import com.example.cargo.repository.SizeRepository;
import com.example.cargo.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeEntityRepository;
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public Size save(Size size) {
        return sizeEntityRepository.save(size);
    }

    @Override
    public Optional<Size> findById(Long id) {
        return sizeEntityRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public void deleteById(Long id) {
        sizeEntityRepository.deleteById(id);
    }

}
