<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>注册界面</title>
    <!-- Libraries -->
    <link type="text/css" href="<%= basePath %>resources/css/index.css" rel="stylesheet"/>
    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <!-- End of Libraries -->
    <script type="text/javascript">
        //刷新验证码
        function reloadCode() {
            var time = new Date().getTime(); //加入时间参数，缓存才会认为是不同请求
            <%--document.getElementById("imagecode").src = "<%=request.getContextPath()%>/servlet/ImageServlet?d="+time;--%>
            $("#imagecode").attr('src', "<%=path%>/blog/imageGenerate?d=" + time);
        }


        //检查重复
        //注意：此处若使用异步方法，则return go 返回的有可能是初始值，应为post方法等待url的返回结果，从而没有改变go的值
        function registerSubmit() {
            var gonext = false;
            var nickname = $("input[name='nickname']").val();
            var username = $("input[name='username']").val();
            var checkcode = $("input[name='checkcode']").val();
            var authorname = encodeURI(encodeURI(nickname));
            var Url = '/blog/' + authorname + '/' + username + '/' + checkcode + '/registerSubmit';
//            $.post(Url, function (result) {
//                if(result['success']){
//                    gonext = true;
//                }
//                else{
//                    $('#accountInfo').html(result['message']).show();
//                    gonext = false;
//                }
//            });
            $.ajax({
                url: Url,
                async: false, // 注意此处需要同步，因为返回完数据后，下面才能
                type: "POST",
                dataType: "json",
                success: function (result) {
                    if (result['success']) {
                        gonext = true;
                    }
                    else {
                        $('#accountInfo').html(result['message']).show();
                        gonext = false;
                    }
                }
            });
            return gonext;
        }
    </script>
</head>
<body>
<section class="login-form-wrap">
    <h1>欢迎注册</h1>
    <%--<div id="accountInfo" style="visibility: hidden;">注册信息出错</div>--%>
    <%--onsubmit标签在提交提交表单是触发，函数 checkForm() 在提交按钮被点击时触发。此函数向用户显示一段消息。--%>
    <form class="login-form" method="post" action="<%= path %>/blog/registerSub" onclick="return registerSubmit()">
        <label>
            <input type="text" name="nickname" id="nickname" required placeholder="昵称">
        </label>
        <label>
            <input type="text" name="username" id="username" required placeholder="登录账号">
        </label>
        <label>
            <input type="text" name="password" id="password" required placeholder="密码">
        </label>
        <label>
            <input type="text" name="checkcode" id="checkcode" required placeholder="验证码">
            <img alt="验证码" id="imagecode" src="<%=path%>/blog/imageGenerate" onclick="reloadCode()"/>
            <%--<a id="blur" href="javascript:reloadCode();">看不清楚</a>--%>
            <%--<a id="blur" onclick="reloadCode()">看不清楚</a>--%>
        </label>
        <input type="submit" value="注册">
    </form>
    <a href="<%=path%>/blog/login">已有账号，去登录</a>
</section>
<div id="accountInfo" style="display: none">注册信息出错!</div>
</body>
</html>

