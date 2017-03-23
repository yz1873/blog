<%--
  Created by IntelliJ IDEA.
  User: Zhang Yu
  Date: 2017/3/22
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <meta charset="UTF-8">
    <title>ueditor demo</title>

    <link type="text/css" rel="stylesheet" href="<%= basePath %>resources/css/ued.css">

    <script type="text/javascript" src="<%= basePath %>ueditor/lang/zh-cn/zh-cn.js"></script>

    <script type="text/javascript" src="<%= basePath %>ueditor/ueditor.config.js"></script>

    <script type="text/javascript" src="<%= basePath %>ueditor/ueditor.all.js"></script>
</head>
<body>
<form action="#" method="get" class="basic-grey">
    <h1>文章编写</h1>
    <label>
        <span>文章标题</span>
        <input id="name" type="text" name="title"/>
    </label>
    <label>
        <span>文章简介</span>
        <textarea id="summary" name="summary"></textarea>
    </label>
    <label>
        <span>文章内容</span>
        <textarea id="container" name="contents"></textarea>
        <script type="text/javascript" charset="utf-8">
            //UE.getEditor('container')为了使ueditor自适应宽度，添加{initialFrameWidth: null }
            UE.getEditor('container', {initialFrameWidth: null});
        </script>
    </label>
    <input type="submit" value="编辑完成" class="button"/>
</form>
</body>
</html>
