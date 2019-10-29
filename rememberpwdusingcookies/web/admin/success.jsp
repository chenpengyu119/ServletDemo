<%@ page import="com.bjsxt.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: pengyu
  Date: 2019/7/15
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <%
        String basePath =
                // 协议
                request.getScheme()+"://"+
                        request.getServerName()+":"+
                        request.getServerPort()+
                        //当前项目
                        request.getContextPath()+"/";
    %>
    <base href="<%=basePath%>">
</head>
<body>
<%--<%
    HttpSession session1 = request.getSession();
    Object obj =session1.getAttribute("user");
    User user = null;
    String uname = "";
    if (obj!=null) {
        user = (User)obj;
        uname = user.getRealname();
    }

%>--%>
欢迎${sessionScope.user.realname}<%--<%=uname%>--%><a href="/remember/servlet/UserServlet?method=logout">注销</a><br>
网站访问人数：<%= application.getAttribute("count")%>
<br>
<a href="/remember/admin/show.jsp">show.jsp</a>
<%--<%=session1.getId()%>--%>
${sessionScope.id}<%--如何获取sessionid--%>

<a href="servlet/UserServlet?method=findAll">查询用户信息</a>
</body>
</html>
