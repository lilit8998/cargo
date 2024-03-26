package com.example.cargo.mapper;

import com.example.cargo.dto.OrderResponseDto;
import com.example.cargo.dto.SaveOrderDto;
import com.example.cargo.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDate;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = LocalDate.class)
public interface OrderMapper {
    OrderResponseDto map(Orders orders);

    @Mapping(target = "sendDate", expression = "java(LocalDate.now())")
    Orders map(SaveOrderDto orderDto);

}
