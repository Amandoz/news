package com.news.dao;

import com.news.model.News;

import java.util.List;

public interface NewsDao {
    News findNewsById(Long id);
    List<News> findAllNews();
    News saveNews(News news);
    News editNews(News news);
    News deleteNews(News news);
    List<News> findNewsByTitle(String title);
}
