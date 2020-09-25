package com.news.service;

import com.news.model.News;

import java.util.List;

public interface NewsService {
    News findNewsById(Long id);
    List<News> findAllNews();
    News saveNews(News news);
    News editNews(News news);
    News deleteNews(News news);
    List<News> findNewsByTitle(String title);
}
