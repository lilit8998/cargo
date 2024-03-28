package com.example.cargo.service;

import com.example.cargo.entity.News;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    News save(News news);
    List<News> findAll();
    Optional<News> findNewsById(int id);
    void deleteById(int id);
}
