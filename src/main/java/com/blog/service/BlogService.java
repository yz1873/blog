package com.blog.service;

import com.blog.entity.Article;
import com.blog.entity.Author;

import java.util.List;

/**
 * Created by Zhang Yu on 2017/3/10.
 */
public interface BlogService {

    /**
     * 返回文章总数
     * @return
     */
    int getArticleCount();

    /**
     * 返回文章列表
     * @return
     */
    List<Article> getArticleList(int offset, int limit);

    /**
     * 根据id返回文章
     * @return
     */
    Article getArticleById(long ArticleId);

    /**
     * 根据Id返回作者姓名
     * @return
     */
    String getAuthorNameById(long AuthorId);

    /**
     * 根据username返回作者对象
     * @return
     */
    Author getByUsername(String username);

    /**
     * 根据authorName返回作者对象
     * @param authorName
     * @return
     */
    Author getByAuthorname(String authorName);

    /**
     * 判断账号和密码是否正确
     * @param username
     * @param password
     * @return
     */
    Author getByUsernameAndPassword(String username,String password);

}
