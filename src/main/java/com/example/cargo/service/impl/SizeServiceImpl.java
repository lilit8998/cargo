package com.example.cargo.service.impl;

import com.example.cargo.entity.Size;
import com.example.cargo.repository.SizeEntityRepository;
import com.example.cargo.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeEntityRepository sizeEntityRepository;
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public Size save(Size sizeEntity) {
        return sizeEntityRepository.save(sizeEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public Optional<Size> findById(int id) {
        return sizeEntityRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public void deleteById(int id) {
        sizeEntityRepository.deleteById(id);
    }

}
