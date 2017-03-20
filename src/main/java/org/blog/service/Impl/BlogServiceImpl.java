package org.blog.service.Impl;

import org.blog.dao.ArticleDao;
import org.blog.dao.AuthorDao;
import org.blog.dao.RoleDao;
import org.blog.entity.Article;
import org.blog.entity.Author;
import org.blog.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zhang Yu on 2017/3/10.
 */
@Service
public class BlogServiceImpl implements BlogService {

    //日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //注入service依赖
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Article> getArticleList(int offset, int limit) {
        return articleDao.queryAll(offset,limit);
    }

    @Override
    public Article getArticleById(long ArticleId) {
        return articleDao.queryById(ArticleId);
    }

    @Override
    public String getAuthorNameById(long AuthorId) {
        return authorDao.authorNameById(AuthorId);
    }

    @Override
    public int getArticleCount() {
        return articleDao.countOfArticle();
    }

    @Override
    public Author getByUsername(String username) {
        return authorDao.getByUsername(username);
    }

    @Override
    public String getuserRoleName(long authorId) {
        return roleDao.userRoleName(authorId);
    }
}
