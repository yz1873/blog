package com.blog.dao;

import com.blog.entity.Author;
import org.apache.ibatis.annotations.Param;

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

    Author getByAuthorname(String authorName);

    Author getByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

}
