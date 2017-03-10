package org.blog.dao;

import org.blog.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test,junit
 */
//配置spring和junit整合
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})

public class ArticleDaoTest {

    //注入Dao实现类依赖
    @Resource
    private ArticleDao articleDao;

    @Test
    public void testQueryById() throws Exception {
        long articleId = 1000;
        Article article = articleDao.queryById(articleId);
        System.out.println(article);
        System.out.println(articleDao.countOfArticle());
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Article> articles = articleDao.queryAll(0, 100);
        for (Article a : articles) {
            System.out.println(a);
        }
    }
}