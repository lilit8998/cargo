package com.example.cargo.mapper;

import com.example.cargo.dto.OrderResponseDto;
import com.example.cargo.dto.SaveOrderDto;
import com.example.cargo.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = LocalDateTime.class)
public interface OrderMapper {
    OrderResponseDto map(Orders orders);

    @Mapping(target = "sendDate", expression = "java(LocalDateTime.now())")
    Orders map(SaveOrderDto orderDto);

}
