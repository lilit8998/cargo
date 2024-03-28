package com.example.cargo.service;

import com.example.cargo.entity.MessageForAdmin;

import java.util.List;
import java.util.Optional;

public interface MessageForAdminService {
    MessageForAdmin save(MessageForAdmin messageForAdmin);
    List<MessageForAdmin> findAll();
    Optional<MessageForAdmin> findMessageForAdminById(int id);
    void deleteById(int id);

}
