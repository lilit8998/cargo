package com.example.cargo.service;

import com.example.cargo.dto.NewsResponseDto;
import com.example.cargo.dto.SaveNewsDto;
import com.example.cargo.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    NewsResponseDto save(SaveNewsDto saveNewsDto);
    List<NewsResponseDto> getAll();
    Optional<News> findNewsById(int id);
    void deleteById(int id);

    Page<News> findAll(Pageable pageable);
}
