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
</head>

<body>
<nav class="navbar">
    <div class="container navbar-content">
        <a href="#">首页</a>
        <a href="#">关于我们</a>
    </div>
</nav>

<img src="<%= basePath %>resources/images/bg.jpg" class="index-bg">

<div class="container">
    <h1>BLOG系统</h1>
    <h4></h4>

    <div class="news-list">
        <div class="news-list-left">

            <c:forEach items="${list}" var="ba">
                <div class="news-list-item">
                    <div class="author-time">
                        <span>慕女神</span> 发表于 <span><fmt:formatDate value="${ba.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                    </div>
                    <div class="news-des">
                        <h3 class="news-title"><i></i><a href="#">${ba.title}</a></h3>

                        <div class="news-content-des">
                                ${ba.content}
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
                        哈哈哈。</p>
                </div>
            </div>
        </div>

        <footer class="copyright">
            Copyright &nbsp; 2017 DreamBoy All rights reserved.
        </footer>
    </div>
</div>
</body>
</html>
