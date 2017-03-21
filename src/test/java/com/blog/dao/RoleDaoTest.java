package com.blog.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

//配置spring和junit整合
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})

public class RoleDaoTest {


    //注入Dao实现类依赖
    @Resource
    private RoleDao roleDao ;

    @Test
    public void testUserRoleName() throws Exception {
        long authorId = 100;
        String roleName = roleDao.userRoleName(authorId);
        System.out.println("role名称为：" + roleName);
    }
}