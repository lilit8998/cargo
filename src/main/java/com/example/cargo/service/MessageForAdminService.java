package com.example.cargo.service;

import com.example.cargo.dto.MessageForAdminResponseDto;
import com.example.cargo.dto.SaveMessageForAdminDto;
import com.example.cargo.entity.MessageForAdmin;

import java.util.List;

public interface MessageForAdminService {
    MessageForAdminResponseDto save(SaveMessageForAdminDto messageForAdmin);
    List<MessageForAdminResponseDto> getAll();
    MessageForAdmin findMessageForAdminById(int id);
    void deleteById(int id);

}
