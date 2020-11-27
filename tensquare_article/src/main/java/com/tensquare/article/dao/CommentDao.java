package com.tensquare.article.dao;

import com.tensquare.article.pojo.Article;
import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 * @author yjk
 * @description CommentDao
 * @date 2020/11/27
 */
public interface CommentDao extends MongoRepository<Comment, String> {
        public List<Article> findbyId(String articleId);
}
