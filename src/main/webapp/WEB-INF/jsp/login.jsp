<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
  <title>登陆界面</title>
  <!-- Libraries -->
  <link type="text/css" href="<%= basePath %>resources/css/index.css" rel="stylesheet" />
  <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
  <!-- End of Libraries -->
</head>
<body>
<section class="login-form-wrap">
  <h1>欢迎登陆</h1>
  <div id="accountInfo" style="visibility: hidden;">登录信息出错</div>
  <form class="login-form" method="post" action="<%= path %>/blog/submit">
    <label>
      <input type="text" name="username" required placeholder="账号">
    </label>
    <label>
      <input type="password" name="password" required placeholder="密码">
    </label>
    <input type="submit" value="登陆">
  </form>
  <a href="<%=path%>/blog/register">无账号，去注册</a>
</section>
</body>
</html>

