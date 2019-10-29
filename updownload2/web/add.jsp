<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/05/28
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


    <form action="/updownload2/servlet/AddServlet" method="post" enctype="multipart/form-data" >
        用户名:<input type="text" name="name"><br>
        年龄:<input type="text" name="age"><br>
        分数:<input type="text" name="score"><br>
        文件：<input type="file" name="photo"><br>
        <input type="submit" value="提交">
    </form>

${error}
</body>
</html>
