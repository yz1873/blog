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
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <!-- End of Libraries -->
    <script type="text/javascript">
        function loginSubmit() {
            var go = false;
            var username = $("input[name='username']").val();
            var password = $("input[name='password']").val();
            var Url = '/blog/blog/' + username + '/' + password + '/loginSubmit';
//注意：此处若使用异步方法，则return go 返回的有可能是初始值，应为post方法等待url的返回结果，从而没有改变go的值
//      $.post(Url, function (result) {
//        if(result['success']){
//          go = true;
//        }
//        else{
//          $('#accountInfo').show();
//          go = false;
//          alert(go);
//        }
//      });
//      alert(go);

            $.ajax({
                url: Url,
                async: false, // 注意此处需要同步，因为返回完数据后，下面才能
                type: "POST",
                dataType: "json",
                success: function (result) {
                    if (result['success']) {
                        go = true;
                    }
                    else {
                        $('#accountInfo').show();
                        go = false;
                    }
                }
            });
            return go;
        }
    </script>
</head>
<body>
<section class="login-form-wrap">
    <h1>欢迎登陆</h1>

    <form class="login-form" method="post" action="<%= path %>/blog/submit" onclick="return loginSubmit()">
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
<div id="accountInfo" style="display: none">用户名不存在或密码出错！</div>
</body>
</html>

