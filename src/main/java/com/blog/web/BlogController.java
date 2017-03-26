package com.blog.web;

import com.baidu.ueditor.ActionEnter;
import com.blog.dto.SeckResult;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;
import java.util.Random;

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
     * 退出登录，删除登录信息
     *
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

    /**
     * 关于我们页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
    public String aboutUs(Model model) {
        return "aboutUs";
    }


    /**
     * 有关ueditor的配置
     *
     * @param request
     * @param response
     */
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

    /**
     * ueditor编辑界面，用于新加文章
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/ued", method = RequestMethod.GET)
    public String ued(Model model) {
        return "ued";
    }

//    @RequestMapping(value = "/submitArticle", method = RequestMethod.POST)
//    public String submitArticle(String title, String summary,String contents) {
//
//    }

    /**
     * 生成验证码图片
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/imageGenerate")
    public void imageGenerate(HttpServletRequest request, HttpServletResponse response) {
        BufferedImage bi = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB); //图像宽度68，高度22,图片类型为BufferedImage.TYPE_INT_RGB
        Graphics g = bi.getGraphics();
        Color c = new Color(200, 150, 255);//设置颜色
        g.setColor(c);                   //g设置颜色
        g.fillRect(0, 0, 68, 22);           //图片设置边框

        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray(); //设置字符集
        Random r = new Random();
        int len = ch.length;
        int index;
        StringBuffer sb = new StringBuffer(); //保存生成的字符
        for (int i = 0; i < 4; i++) {
            index = r.nextInt(len);//该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n
            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255))); //为每个字体随机设置一个颜色
            g.drawString(ch[index] + "", (i * 15) + 3, 18);//str - 参数为（要绘制的string,x坐标,y 坐标）
            sb.append(ch[index]);
        }
        request.getSession().setAttribute("piccode", sb.toString()); //将字符存在session中，用于后面的比较
        try {
            ImageIO.write(bi, "JPG", response.getOutputStream()); //将图像输出
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册界面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        return "register";
    }


    //不需要model，直接返回json
    //ajax ,json暴露秒杀接口的方法
    @RequestMapping(value = "/{authorname}/{username}/{checkcode}/registerSubmit", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    //produces告诉浏览器我们的content的type，为application/json
    @ResponseBody //当springmvc看到这个注解时，会试图将我们的返回类型包装成json
    public SeckResult registerSubmit(@PathVariable("authorname") String authorname,
                                     @PathVariable("username") String username,
                                     @PathVariable("checkcode") String checkcode,
                                     HttpServletRequest request) {
        boolean usernameQualified = false;
        boolean authorNameQualified = false;
        boolean checkcodeQualified = false;
        try {
            request.setCharacterEncoding("gbk");
            if (blogService.getByUsername(username) != null) {
                usernameQualified = true;
            }
            String an = java.net.URLDecoder.decode(authorname, "utf8");
            if (blogService.getByAuthorname(an) != null) {
                authorNameQualified = true;
            }
            String piccode = (String) request.getSession().getAttribute("piccode");//取得图片中的字符串//取得用户输入的字符串
            checkcode = checkcode.toUpperCase();
            if (checkcode.equals(piccode)) checkcodeQualified = true;

            if (usernameQualified) {
                return new SeckResult(false,"用户名重复！请更换！");
            } else if (authorNameQualified) {
                return new SeckResult(false,"昵称重复！请更换！");
            } else if (!checkcodeQualified) {
                return new SeckResult(false,"验证码错误！");
            } else {
                return new SeckResult(true,"注册成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SeckResult(false,"未知错误！");
    }

}