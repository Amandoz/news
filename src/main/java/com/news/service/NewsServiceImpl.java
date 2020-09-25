package com.news.service;

import com.news.dao.NewsDao;
import com.news.model.News;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    final NewsDao newsDao;

    public NewsServiceImpl(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public News findNewsById(Long id) {
        return newsDao.findNewsById(id);
    }

    @Override
    public List<News> findAllNews() {
        return newsDao.findAllNews();
    }

    @Override
    public News saveNews(News news) {
        return newsDao.saveNews(news);
    }

    @Override
    public News editNews(News news) {
        return newsDao.editNews(news);
    }

    @Override
    public News deleteNews(News news) {
        return newsDao.deleteNews(news);
    }

    @Override
    public List<News> findNewsByTitle(String title) {
        return newsDao.findNewsByTitle(title);
    }
}
