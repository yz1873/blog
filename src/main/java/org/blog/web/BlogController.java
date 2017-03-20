package org.blog.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.blog.entity.Article;
import org.blog.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Zhang Yu on 2017/3/10.
 */
@Controller
@RequestMapping("/blog") //url:模块/资源/{}/细分
public class BlogController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        //model用于存放所有用于渲染list.jsp的数据
        //model + list.jsp = ModelAndView

        int limit = 20;
        //获取列表页
        List<Article> list = blogService.getArticleList(0, limit);
        model.addAttribute("list", list);

        return "list"; //根据前面配置的前缀和后缀，此处代表/WEB-INF/jsp/list.jsp
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(String username, String password) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println("登录失败错误信息");
            token.clear();
        }
        return "redirect:/blog/list";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return "redirect:/blog/list";
    }
}
