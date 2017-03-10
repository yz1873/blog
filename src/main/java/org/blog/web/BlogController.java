package org.blog.web;

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
        List<Article> list = blogService.getArticleList(0,limit);
        model.addAttribute("list", list);

        return "list"; //根据前面配置的前缀和后缀，此处代表/WEB-INF/jsp/list.jsp
    }
}
