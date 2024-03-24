package com.example.cargo.mapper;

import com.example.cargo.dto.OrderResponseDto;
import com.example.cargo.dto.SaveOrderDto;
import com.example.cargo.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring", imports = LocalDate.class)
public interface OrderMapper {
    OrderResponseDto map(Orders orders);
    @Mapping(target = "sendDate",source = "deliveredDate",expression = "java(java.time.localDate.now()")
    Orders map(SaveOrderDto orderDto);
}
