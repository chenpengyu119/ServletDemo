<%--
  Created by IntelliJ IDEA.
  User: pengyu
  Date: 2019/7/10
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录处理</title>
</head>
<body>
<%
    String uname = request.getParameter("uname");
    String pwd = request.getParameter("pwd");
    boolean res = false;
    if (uname!=null && pwd != null &&uname.equals("admin") && pwd.equals("toor")){
        res = true;
    }
    response.getWriter().write(res?"登录成功":"登录失败");
%>
</body>
</html>
