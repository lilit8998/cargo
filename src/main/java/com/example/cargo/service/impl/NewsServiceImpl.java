package com.example.cargo.service.impl;

import com.example.cargo.dto.NewsResponseDto;
import com.example.cargo.dto.SaveNewsDto;
import com.example.cargo.entity.News;
import com.example.cargo.mapper.NewsMapper;
import com.example.cargo.repository.NewsRepository;
import com.example.cargo.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public NewsResponseDto save(SaveNewsDto saveNewsDto) {
        News news = newsMapper.map(saveNewsDto);
        return newsMapper.map(newsRepository.save(news));
    }

    @Override
    public List<NewsResponseDto> getAll() {
        List<News> all = newsRepository.findAll();
        List<NewsResponseDto> newsResponseDto = new ArrayList<>();
        for (News news : all) {
            newsResponseDto.add(newsMapper.map(news));
        }
        return newsResponseDto;
    }

    @Override
    public Optional<News> findNewsById(int id) {
        return newsRepository.findById(id);
    }

    @Override
    public Page<News> findAll(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void deleteById(int id) {
        newsRepository.deleteById(id);
    }
}
