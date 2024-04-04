package com.example.cargo.mapper;

import com.example.cargo.dto.MessageForAdminResponseDto;
import com.example.cargo.dto.SaveMessageForAdminDto;
import com.example.cargo.entity.MessageForAdmin;
import org.mapstruct.Mapper;

import java.time.LocalDate;

@Mapper(componentModel = "spring", imports = LocalDate.class)
public interface MessageForAdminMapper {

    MessageForAdminResponseDto map(MessageForAdmin messageForAdmin);

    MessageForAdmin map(SaveMessageForAdminDto messageForAdminDto);
}
