<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/31 0031
  Time: 下午 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>注册完成</title>
  <link type="text/css" href="<%= basePath %>resources/css/finishRegister.css" rel="stylesheet"/>
</head>
<body>
<script type="text/javascript">
  var t=3;//设定跳转的时间
  setInterval("refer()",1000); //启动1秒定时
  function refer(){
    if(t==0){
      location="<%= path %>/blog/list"; //#设定跳转的链接地址
    }
    document.getElementById('show').innerHTML=""+t+"秒后跳转到主页"; // 显示倒计时
    t--; // 计数器递减
  }
</script>
<div class="container">
  <span>注册完成！</span>
  <span id="show"></span>
</div>
</body>
</html>
