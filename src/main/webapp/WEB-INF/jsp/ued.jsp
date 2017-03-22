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
  <script type="text/javascript" src="<%= basePath %>ueditor/lang/zh-cn/zh-cn.js" ></script>

  <script type="text/javascript" src="<%= basePath %>ueditor/ueditor.config.js"></script>

  <script type="text/javascript" src="<%= basePath %>ueditor/ueditor.all.js"></script>
</head>
<body>
<form action="#" method="get">
  <textarea id="container" name="contents">UEditor Demo</textarea>
  <script type="text/javascript" charset="utf-8">
    UE.getEditor('container');
  </script>
  <input type="submit" value="submit" />
</form>
</body>
</html>
