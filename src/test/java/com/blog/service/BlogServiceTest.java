package com.blog.service;

import com.blog.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class BlogServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlogService blogService;

    @Test
    public void testGetArticleCount() throws Exception {
        int count = blogService.getArticleCount();
        logger.info("count={}",count);
    }

    @Test
    public void testGetArticleList() throws Exception {
        List<Article> list = blogService.getArticleList(0, 5);
        logger.info("list={}",list);
    }

    @Test
    public void testGetArticleById() throws Exception {
        Article article = blogService.getArticleById(1000);
        logger.info("article={}",article);
    }

    @Test
    public void testGetAuthorNameById() throws Exception {
        String author = blogService.getAuthorNameById(100);
        logger.info("author={}",author);
    }

    @Test
    public void testName() throws Exception {
        logger.info("是否存在={}",blogService.getByUsername("123"));
    }
}