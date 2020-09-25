package com.news.dao;

import com.news.model.Comments;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CommentsDaoImpl implements CommentsDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Comments save(Comments comments) {
        entityManager.persist(comments);
        return comments;
    }
}
