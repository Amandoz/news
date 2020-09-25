package com.news.service;

import com.news.dao.CommentsDao;
import com.news.model.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentsServiceImpl implements CommentsService {
    final CommentsDao commentsDao;

    public CommentsServiceImpl(CommentsDao commentsDao) {
        this.commentsDao = commentsDao;
    }

    @Override
    public Comments save(Comments comments) {
        return commentsDao.save(comments);
    }
}
