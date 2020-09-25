package com.news.dao;

import com.news.model.News;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NewsDaoImpl implements NewsDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public News findNewsById(Long id) {
        return entityManager.createQuery("SELECT n FROM News n WHERE n.id = :id", News.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<News> findAllNews() {
        return entityManager.createQuery("SELECT n FROM News n", News.class)
                .getResultList();
    }

    @Override
    public News saveNews(News news) {
        entityManager.persist(news);
        return news;
    }

    @Override
    public News editNews(News news) {
        return entityManager.merge(news);
    }

    @Override
    public News deleteNews(News news) {
        entityManager.remove(news);
        return news;
    }

    @Override
    public List<News> findNewsByTitle(String title) {
        List<News> result = new ArrayList<>();
        for (News news : findAllNews()) {
            if ((news.getTitle().toLowerCase().contains(title.toLowerCase())) || (news.getDescription().toLowerCase().contains(title.toLowerCase()))) {
                result.add(news);
            }
        }
        return result;
    }
}
