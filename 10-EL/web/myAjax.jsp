<%--
  Created by IntelliJ IDEA.
  User: pengyu
  Date: 2019/7/16
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>myAjax</title>
    <script type="text/javascript">
        function getData() {
            // 创建ajax引擎对象
            var  ajax;
            if (window.XMLHttpRequest) {// 这是火狐的，但是现在新版本都支持
                ajax = new XMLHttpRequest();
            }else if(window.ActiveXObject){// 这时ie6，7的
                ajax = new ActiveXObject("Msxm12.XMLHTTP");
            }
            // 复写onreadystatechange函数
            ajax.onreadystatechange = function () {
                if (ajax.readyState==4){
                    if (ajax.status==200){
                        // 获取响应数据
                        var result = ajax.responseText;
                        // 获取元素
                        var show = document.getElementById("showdiv");
                        show.innerHTML = result;
                    } else if (ajax.status==404){
                        var show = document.getElementById("showdiv");
                        show.innerHTML = "请求资源不存在";
                    }else if(ajax.status==500){
                        var show = document.getElementById("showdiv");
                        show.innerHTML = "服务器繁忙";
                    }
                }else {
                    var show = document.getElementById("showdiv");
                    show.innerHTML = "<img src=img/wait.gif width='100px'>";
                }
            }
            // 发送请求
            ajax.open("get","servlet/AjaxServlet");
            ajax.send(null);// 这里的参数是post请求参数，如果是get，则直接拼接到url，对于ie，不传可以，火狐不传报错，所以传null
        }
    </script>
</head>
<body>
<input type="button" onclick="getData()" value="获取数据">
<div id="showdiv" style="border: yellow solid 1px;
width: 300px;
height: 100px">hhh</div>
</body>
</html>
