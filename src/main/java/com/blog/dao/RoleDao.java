package com.blog.dao;

/**
 * Created by Zhang Yu on 2017/3/17.
 */
public interface RoleDao {
    /**
     * 根据作者ID查找其角色名称
     * @param authorId
     * @return
     */
    String userRoleName (long authorId);
}
