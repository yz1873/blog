package org.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.blog.entity.Article;

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

}
