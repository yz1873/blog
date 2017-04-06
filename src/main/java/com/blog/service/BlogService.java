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
     * 返回本作者所有文章
     * @param authorId
     * @param offset
     * @param limit
     * @return
     */
    List<Article> queryAllArticlesByAuthorId(long authorId,int offset,int limit);

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

    /**
     * 添加作者
     * @param authorname
     * @param username
     * @param password
     */
    void addAuthor(String authorname,String username,String password);

    /**
     * 根据username查id
     * @param username
     * @return
     */
    long authorIdByName(String username);


    /**
     * 新建文章
     * @param authorId
     * @param title
     * @param summary
     * @param contents
     */
    void insertArticle(long authorId, String title, String summary, String contents);


    /**
     * 删除文章
     * @param articleId
     */
    void deleteArticle(long articleId);

    /**
     * 根据articleId得到authorId
     * @param articleId
     * @return
     */
    long authorIdByArticleId(long articleId);

}
