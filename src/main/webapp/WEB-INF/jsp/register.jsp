<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>登陆界面</title>
    <!-- Libraries -->
    <link type="text/css" href="<%= basePath %>resources/css/index.css" rel="stylesheet"/>
    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <!-- End of Libraries -->
    <script type="text/javascript">
        //刷新验证码
        function reloadCode(){
            var time = new Date().getTime(); //加入时间参数，缓存才会认为是不同请求
            document.getElementById("imagecode").src = "<%=path%>/blog/imageGenerate?d="+time;
        }
        //检查重复
        var xmlhttprequest = null;
        var text = null;
        function registerSubmit() {
            if (window.ActiveXObject) {//IE浏览器
                xmlhttprequest = new ActiveXObject("Microsoft.XMLHTTP");
            } else if (window.XMLHttpRequest) {
                xmlhttprequest = new XMLHttpRequest();
            }
            if (null != xmlhttprequest) {
                var text1 = document.getElementById("username").value;
                var text2 = document.getElementById("nickname").value;
                var text3 = document.getElementById("checkcode").value;
                xmlhttprequest.open("POST", "registerSubmit?username=" + text1 + "&authorName" + text2 + "&checkcode" + text3, true);
                xmlhttprequest.onreadystatechange = ajaxcallback;
                xmlhttprequest.send(null);
                if(text == "注册成功！"){
                    text = null;
                    return true;
                }
                else {
                    text = null;
                    return false;
                }
            }
        }
        function ajaxcallback() {
            if (xmlhttprequest.readyState == 4) {
                if (xmlhttprequest.status == 200) {
                    text = xmlhttprequest.responseText;
                    document.getElementById("accountInfo").innerHTML = text;
                    document.getElementById("accountInfo").style.visibility="visible";
                }
            }
        }
    </script>
</head>
<body>
<section class="login-form-wrap">
    <h1>欢迎注册</h1>
    <div id="accountInfo" style="visibility: hidden;">注册信息出错</div>
    <%--onsubmit标签在提交提交表单是触发，函数 checkForm() 在提交按钮被点击时触发。此函数向用户显示一段消息。--%>
    <form class="login-form" method="post" action="<%= path %>/blog/registerSubmit" onsubmit="return registerSubmit()">
        <label>
            <input type="text" name="nickname" required placeholder="昵称">
        </label>
        <label>
            <input type="text" name="username" id="username" required placeholder="登录账号">
        </label>
        <label>
            <input type="text" name="password" id="password" required placeholder="密码">
        </label>
        <label>
            <input type="text" name="checkcode" id="checkcode" required placeholder="验证码">
            <img alt="验证码" id="imagecode" src="<%=path%>/blog/imageGenerate"/>
            <a id="blur" href="javascript:reloadCode();">看不清楚</a>
        </label>
        <input type="submit" value="注册">
    </form>
    <a href="<%=path%>/blog/login" >已有账号，去登录</a>
</section>
</body>
</html>

