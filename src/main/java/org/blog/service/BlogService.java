package org.blog.service;

import org.blog.entity.Article;

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
}
