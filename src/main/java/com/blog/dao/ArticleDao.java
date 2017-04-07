package com.blog.dao;

import com.blog.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Zhang Yu on 2017/3/9.
 */
public interface ArticleDao {

    /**
     * 按照id查询文章
     *
     * @param articleId
     * @return article
     */
    Article queryById(long articleId);

    /**
     * 按照位移查看一定数量的文章
     *
     * @param offset
     * @param limit
     * @return 文章list
     */
    List<Article> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 按照位移查看一定数量的本作者的文章
     * @param offset
     * @param limit
     * @return
     */
    List<Article> queryAllArticlesByAuthorId(@Param("authorId") long authorId,@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查看数据库中文章总数
     * @return 文章数
     */
    int countOfArticle();

    /**
     * 根据authorid查看该作者数据库中文章总数
     * @return 文章数
     */
    int countOfArticleByAuthorId(long authorId);

    /**
     * 新增文章
     * @param authorId
     * @param title
     * @param summary
     * @param contents
     */
    void insertArticle(@Param("authorId") long authorId, @Param("title") String title, @Param("summary") String summary, @Param("contents") String contents);

    /**
     * 根据文章id删除文章
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
