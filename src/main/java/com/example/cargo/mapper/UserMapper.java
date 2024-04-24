package com.example.cargo.mapper;

import com.example.cargo.dto.UserDto;
import com.example.cargo.dto.UserResponseDto;
import com.example.cargo.entity.User;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = LocalDateTime.class)
public interface UserMapper {
    UserResponseDto map(User user);

    User map(@Valid UserDto userResponseDto);

    void updateUserFromDto(UserDto dto, @MappingTarget User user);
}
