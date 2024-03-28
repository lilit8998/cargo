package com.example.cargo.service.impl;

import com.example.cargo.entity.News;
import com.example.cargo.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsServiceImpl NewsRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public News save(News news) {
        return NewsRepository.save(news);
    }

    @Override
    public List<News> findAll() {
        return NewsRepository.findAll();
    }

    @Override
    public Optional<News> findNewsById(int id) {
        return NewsRepository.findNewsById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void deleteById(int id) {
        NewsRepository.deleteById(id);
    }
}
