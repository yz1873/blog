package org.blog.dao;

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
@ContextConfiguration({"classpath:spring/spring-dao.xml"})

public class AuthorDaoTest {

    //注入Dao实现类依赖
    @Resource
    private AuthorDao authorDao;

    @Test
    public void testAuthorNameById() throws Exception {
        long authorId = 100;
        String authorName = authorDao.authorNameById(authorId);
        System.out.println(authorName);
    }
}