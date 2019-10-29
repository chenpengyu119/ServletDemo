<%--
  Created by IntelliJ IDEA.
  User: pengyu
  Date: 2019/7/11
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        $(function () {
            // 显示隐藏密码
            $("#showpwd").click(function () {
                var pwd = $("#pwd");
                if (pwd.attr("type") === "text") {
                    pwd.attr("type","password");
                }else {
                    pwd.attr("type","text");
                }
            });

        })

        // js验证用户名
        function checkUser() {
            var unameval = document.getElementById("uname").value;
            if (unameval === ""){
                document.getElementById("unameerror").innerHTML = "用户名不能为空";
                return false;
            }
            return true;
        }

        //js 验证密码
        function checkPwd() {
            var pwdval = document.getElementById("pwd").value;
            if (pwdval.length<=0){
                document.getElementById("pwderror").innerHTML = "密码不能为空";
                return false;
            }
            return true;
        }

        // js验证表单
        function checkForm() {
            var unamecheck = checkUser();
            var pwdcheck = checkPwd();
            if (unamecheck && pwdcheck){
                return true;
            } else {
                return false;
            }
        }

    </script>
</head>
<body>
<h3>用户登录</h3>
<form action="/myservlet/servlet/DoLoginServlet" method="get" onsubmit="return checkForm()">

    用户名:<input type="text" name="uname" id="uname" onblur="checkUser()">
    <span id="unameerror">
        <%
            String unameerr = (String) request.getAttribute("unameerr");
            if (unameerr != null){
                out.print(unameerr);
            }
        %>
    </span>
    <br>
    密码:<input type="password" id="pwd" name="pwd"> <input id="showpwd" type="button" value="显示密码"  onblur="checkPwd()">
    <span id="pwderror">
        <%
            String pwderr = (String)request.getAttribute("pwderr");
            if (pwderr != null){
                out.print(pwderr);
            }
        %>
    </span>
    <br>
    <input type="submit" value="登录">
</form>
<span id="error">
    <%
        String err = (String) request.getAttribute("err");
        if (err != null) {
            out.print(err);
        }

    %>
</span>
</body>
</html>
