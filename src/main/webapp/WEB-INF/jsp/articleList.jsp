<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>BLOG系统</title>

    <link type="text/css" rel="stylesheet" href="<%= basePath %>resources/css/list.css">

    <script type="text/javascript">
        function delete_confirm() {
            var r = confirm("确定删除？")
            if (r == true) {
                return true;
            }
            else {
                return false;
            }
        }
    </script>
</head>

<body>
<nav class="navbar">
    <div class="container navbar-content">
        <a href="<%=path%>/blog/list">首页</a>
        <a href="<%=path%>/blog/aboutUs">关于我们</a>

        <shiro:authenticated>
            <a class="login" href="<%=path%>/blog/logout">退出登录</a>
        </shiro:authenticated>

        <shiro:hasRole name="manager">
            <a class="login" href="<%=path%>/blog/articleList">我的文章列表</a>
        </shiro:hasRole>

        <shiro:hasRole name="manager">
            <a class="login" href="<%=path%>/blog/ued">写文章</a>
        </shiro:hasRole>
    </div>
</nav>

<img src="<%= basePath %>resources/images/bg.jpg" class="index-bg">

<div class="container">
    <h1>BLOG文章汇聚</h1>
    <h4></h4>

    <div class="news-list">
        <div class="news-list-left">

            <c:forEach items="${articleList}" var="ba">
                <div class="news-list-item">
                    <div class="author-time">
                        <span>${ba.authorName}</span> 发表于 <span><fmt:formatDate value="${ba.createTime}"
                                                                                pattern="yyyy-MM-dd HH:mm:ss"/></span>
                        <shiro:hasRole name="manager">
                            <a class="update" href="<%=path%>/blog/${ba.articleId}/delete"
                               onclick="return delete_confirm()">删除</a>
                        </shiro:hasRole>
                        <shiro:hasRole name="manager">
                            <a class="update" href="<%=path%>/blog/${ba.articleId}/update">修改</a>
                        </shiro:hasRole>
                    </div>
                    <div class="news-des">
                        <h3 class="news-title"><i></i><a href="<%=path%>/blog/${ba.articleId}/content">${ba.title}</a>
                        </h3>

                        <div class="news-summary-des">
                                ${ba.summary}
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>

        <div class="news-list-right">
            <div class="about">
                <h4>关于我们</h4>

                <div class="about-des">
                    <p>
                        并不知道要写点什么。</p>
                </div>
            </div>
        </div>

        <div class="page">
            <a href="<%=path%>/blog/articleList/0">首页</a>
            <c:if test="${curPage>0}">
                <a href="<%=path%>/blog/articleList/${curPage-1}">上一页</a>
            </c:if>
            <c:if test="${curPage<pageNum}">
                <a href="<%=path%>/blog/articleList/${curPage+1}">下一页</a>
            </c:if>
            <a href="<%=path%>/blog/articleList/${pageNum}">尾页</a>
        </div>
        <div class="page">
            第${curPage+1}页/共${pageNum+1}页
        </div>

        <footer class="copyright">
            Copyright &nbsp; 2017 YuZhang All rights reserved.
        </footer>
    </div>
</div>
</body>
</html>
