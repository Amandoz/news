package com.news.dao;

import com.news.model.Comments;

public interface CommentsDao {
    Comments save(Comments comments);
}
