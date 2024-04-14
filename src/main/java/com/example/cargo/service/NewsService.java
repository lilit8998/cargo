package com.example.cargo.service;

import com.example.cargo.dto.NewsResponseDto;
import com.example.cargo.dto.SaveNewsDto;
import com.example.cargo.entity.News;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NewsService {
    NewsResponseDto save(SaveNewsDto saveNewsDto);
    List<NewsResponseDto> getAll();
    NewsResponseDto findNewsById(int id);
    void deleteById(int id);
}
