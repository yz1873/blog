//存放主要交互逻辑的js代码
// javascript 模块化(package.类.方法)

var blog = {
    reloadCode: function () {
        var time = new Date().getTime(); //加入时间参数，缓存才会认为是不同请求
        document.getElementById("imagecode").src = "<%=path%>/blog/imageGenerate?d=" + time;
    }

}