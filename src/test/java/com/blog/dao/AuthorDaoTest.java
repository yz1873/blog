package com.blog.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test,junit
 */
//配置spring和junit整合
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-shiro.xml"})

public class AuthorDaoTest {

    //注入Dao实现类依赖
    @Resource
    private AuthorDao authorDao;

    @Test
    public void testAuthorNameById() throws Exception {
        long authorId = 1;
        String authorName = authorDao.authorNameById(authorId);
        System.out.println(authorName);
    }

    @Test
    public void testName() throws Exception {
        String authorname = "王五";
        String uname = "wangwu";
        String pass = "890924";
        authorDao.addAuthor(authorname,uname,pass);
        System.out.println(authorDao.getByUsernameAndPassword(uname,pass));
    }
}