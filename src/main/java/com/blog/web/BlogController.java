package com.blog.web;

import com.baidu.ueditor.ActionEnter;
import com.blog.entity.Article;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import com.blog.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    /**
     * 商品列表页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        int limit = 20;
        //获取列表页
        List<Article> list = blogService.getArticleList(0, limit);
        model.addAttribute("list", list);
        return "list"; //根据前面配置的前缀和后缀，此处代表/WEB-INF/jsp/list.jsp
    }

    /**
     * 登录界面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    /**
     * 登录界面提交操作，使用shiro验证用户身份
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            token.clear();
        }
        return "redirect:/blog/list";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return "redirect:/blog/list";
    }

    @RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
    public String aboutUs(Model model) {
        return "aboutUs";
    }



    @RequestMapping(value = "/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        String rootPath = request.getSession()
                .getServletContext().getRealPath("/");

        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/ued", method = RequestMethod.GET)
    public String ued(Model model) {
        return "ued";
    }

//    @RequestMapping(value = "/submitArticle", method = RequestMethod.POST)
//    public String submitArticle(String title, String summary,String contents) {
//
//    }
}
