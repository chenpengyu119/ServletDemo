<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/05/28
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--输出所有的学生信息 -->
<a  href="/updownload2/add.jsp">添加学生信息</a>

<table align="center" border="1" width="70%">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>分数</th>
    </tr>
    <c:forEach items="${requestScope.stuList2}" var="stu" >
        <tr>
            <td>${stu.id}</td>
            <td>${stu.name }</td>
            <td>${stu.age }</td>
            <td>${stu.score}</td>
            <td>
                <img src="/updownload2/servlet/DownServlet?stuId=${stu.id}">
            </td>
            <td>
                <a href="/updownload2/servlet/DownServlet?stuId=${stu.id}">下载</a>
            </td>

        </tr>
    </c:forEach>
</table>


</body>
</html>
