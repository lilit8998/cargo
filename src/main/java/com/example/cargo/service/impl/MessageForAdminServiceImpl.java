package com.example.cargo.service.impl;

import com.example.cargo.dto.MessageForAdminResponseDto;
import com.example.cargo.dto.SaveMessageForAdminDto;
import com.example.cargo.entity.MessageForAdmin;
import com.example.cargo.mapper.MessageForAdminMapper;
import com.example.cargo.repository.MessageForAdminRepository;
import com.example.cargo.service.MessageForAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageForAdminServiceImpl implements MessageForAdminService {
    private final MessageForAdminRepository messageForAdminRepository;
    private final MessageForAdminMapper messageforAdminMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public MessageForAdminResponseDto save(SaveMessageForAdminDto saveMessageForAdminDto) {
        MessageForAdmin messageForAdmin = messageforAdminMapper.map(saveMessageForAdminDto);
        return  messageforAdminMapper.map(messageForAdminRepository.save(messageForAdmin));
    }

    @Override
    public List<MessageForAdminResponseDto> getAll() {
        List<MessageForAdmin> all = messageForAdminRepository.findAll();
        return all.stream()
                .map(messageforAdminMapper::map)
                .collect(Collectors.toList());
    }

    public MessageForAdmin findMessageForAdminById(int id) {
        return messageForAdminRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void deleteById(int id) {
        messageForAdminRepository.deleteById(id);
    }
}
