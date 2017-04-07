<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>新建文章</title>

    <link type="text/css" rel="stylesheet" href="<%= basePath %>resources/css/ued.css">

    <script type="text/javascript" src="<%= basePath %>ueditor/lang/zh-cn/zh-cn.js"></script>

    <script type="text/javascript" src="<%= basePath %>ueditor/ueditor.config.js"></script>

    <script type="text/javascript" src="<%= basePath %>ueditor/ueditor.all.js"></script>

    <script type="text/javascript">
        function submit_confirm() {
            var r = confirm("确定提交？")
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
    </div>
</nav>
<img src="<%= basePath %>resources/images/bg.jpg" class="index-bg">
<form action="<%=path%>/blog/${article.articleId}/submitArticleUpdate" method="post" class="basic-grey">
    <h1>文章编写</h1>
    <label>
        <span>文章标题</span>
        <input id="name" type="text" name="title" value="${article.title}"/>
    </label>
    <label>
        <span>文章简介</span>
        <textarea id="summary" name="summary">${article.summary}</textarea>
    </label>
    <label>
        <span>文章内容</span>
        <textarea id="container" name="contents">${article.content}</textarea>
        <script type="text/javascript" charset="utf-8">
            //UE.getEditor('container')为了使ueditor自适应宽度，添加{initialFrameWidth: null }
            UE.getEditor('container', {initialFrameWidth: null});
        </script>
    </label>
    <input type="submit" value="编辑完成" class="button" onclick="return submit_confirm()"/>
</form>
</body>
</html>
