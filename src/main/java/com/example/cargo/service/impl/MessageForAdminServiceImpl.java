package com.example.cargo.service.impl;

import com.example.cargo.entity.MessageForAdmin;
import com.example.cargo.repository.MessageForAdminRepository;
import com.example.cargo.service.MessageForAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageForAdminServiceImpl implements MessageForAdminService {

    private final MessageForAdminRepository messageForAdminRepository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public MessageForAdmin save(MessageForAdmin messageForAdmin) {
        return messageForAdminRepository.save(messageForAdmin);
    }

    @Override
    public List<MessageForAdmin> findAll() {
        return messageForAdminRepository.findAll();
    }

    @Override
    public Optional<MessageForAdmin> findMessageForAdminById(int id) {
        return messageForAdminRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void deleteById(int id) {
        messageForAdminRepository.deleteById(id);
    }
}
