package com.example.cargo.mapper;

import com.example.cargo.dto.NewsResponseDto;
import com.example.cargo.dto.SaveNewsDto;
import com.example.cargo.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDate;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = LocalDate.class)
public interface NewsMapper {
    NewsResponseDto map(News news);

    @Mapping(target = "publishDate", expression = "java(LocalDate.now())")
    News map(SaveNewsDto saveNewsDto);
}
