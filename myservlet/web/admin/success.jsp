<%--
  Created by IntelliJ IDEA.
  User: pengyu
  Date: 2019/7/10
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
登录成功

<%
// 获取Session
    HttpSession hs = request.getSession();
    Object obj = hs.getAttribute("uname");
    String uname = null;
    if (obj != null){
        // session未失效
        uname = (String)obj;
    }else {
        //session失效之后cookies未失效，还是会转到这个页面
        // session失效，重定向重新登录
        response.sendRedirect("/myservlet/servlet/ck");
    }
%>
欢迎<%=hs.getAttribute("uname")%>访问
<%--业务处理--%>
<form action="/myservlet/servlet/show" method="get">
    <input type="submit" value="查看个人信息"/>
</form>

</body>
</html>
