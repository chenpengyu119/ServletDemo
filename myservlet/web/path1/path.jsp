<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/07/11
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Title</title>

</head>
<body>
    <h3>绝对路径：以http开始，包括ip、port、上下文路径、目录路径、文件名。访问范围所有服务器的所有应用</h3>
    <a href="">同一个应用同一个目录下的文件：insert.jsp</a><br>
    <a href="">同一个应用同级目录下的文件：sub/show.jsp</a><br>
    <a href="">同一个应用其他目录下的文件1：path2/update.jsp</a><br>
    <a href="">同一个应用其他目录下的文件2：index.jsp</a><br>
    <a href="">同一个应用的servlet FirstServlet</a><br>
    <a href="">同一个服务器不同应用cart.html</a><br>
    <a href="">北京尚学堂</a><br>
    <a href="">乔迁之喜！武汉尚学堂乔迁光谷，欢迎考察！</a>

    <h3>根路径，以/开始，/代表当前服务器，访问范围：当前服务器的所有应用</h3>
    <a href="/myservlet/path1/insert.jsp">同一个应用同一个目录下的文件：insert.jsp</a><br>
    <a href="/myservlet/path1/sub/show.jsp">同一个应用同级目录下的文件：sub/show.jsp</a><br>
    <a href="/myservlet/path2/update.jsp">同一个应用其他目录下的文件1：path2/update.jsp</a><br>
    <a href="/myservlet/index.jsp">同一个应用其他目录下的文件2：index.jsp</a><br>
    <a href="/myservlet/servlet/FirstServlet">同一个应用的servlet FirstServlet</a><br>
    <a href="/jsp01/cart/cart.html">同一个服务器不同应用cart.html</a><br>

    <h3>相对路径1：相对于当前文件自身.访问范围：当前服务器的所有应用</h3>
    <%--因为已经设置基准，所以不能访问--%>
    <a href="insert.jsp">同一个应用同一个目录下的文件：insert.jsp</a><br>
    <a href="sub/show.jsp">同一个应用同级目录下的文件：sub/show.jsp</a><br>
    <a href="../path2/update.jsp">同一个应用其他目录下的文件1：path2/update.jsp</a><br>
    <a href="../../../myservlet/web/index.jsp">同一个应用其他目录下的文件2：index.jsp</a><br>
    <a href="../servlet/FirstServlet">同一个应用的servlet FirstServlet</a><br>
    <a href="../../jsp1/cart/cart.html">同一个服务器不同应用cart.html</a><br>


    <h3>相对路径2：相对于基准路径。访问范围：当前服务器的所有应用</h3>
    <a href="path1/insert.jsp">同一个应用同一个目录下的文件：insert.jsp</a><br>
    <a href="path1/sub/show.jsp">同一个应用同级目录下的文件：sub/show.jsp</a><br>
    <a href="path2/update.jsp">同一个应用其他目录下的文件1：path2/update.jsp</a><br>
    <a href="index.jsp">同一个应用其他目录下的文件2：index.jsp</a><br>

    <a href="servlet/FirstServlet">同一个应用的servlet FirstServlet</a><br>
    <a href="../jsp01/cart/cart.html">同一个服务器不同应用cart.html</a><br>


    <h3>request关于路径的方法</h3>
    <%=request.getScheme() %><br>
    <%=request.getServerName() %><br>
    <%=request.getServerPort() %><br>
    <%=request.getContextPath() %><br>

    <!--
    路径的选择
    1.访问其他服务器的资源，只能使用绝对路径
    2.访问当前服务器的其他项目，建议使用根路径
    3.访问当前服务器的当前项目，建议使用相对路径
      建议使用相对路径2

    以上路径理论的适用范围
        只适用HTML标签  <a href=""></a>  <img src="" alt=""> <form action=""></form>
            <link rel="alternate" href="atom.xml" type="application/atom+xml" title="Atom">
            <script !src=""></script>
        不适用于JSP的路径
           转发
           重定向
           文件包含




    -->
</body>
</html>
