package org.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.blog.entity.Author;

/**
 * Created by Zhang Yu on 2017/3/9.
 */
public interface AuthorDao {

    /**
     * 根据作者id返回作者姓名
     *
     * @param authorId
     * @return 姓名
     */
    String authorNameById(long authorId);

    Author getByUsername(String username);

}
