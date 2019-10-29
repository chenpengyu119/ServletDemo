<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pengyu
  Date: 2019/7/16
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Other's JSTL</title>
</head>
<body>
        <%--c:forEache--%>
        <select name="" id="">
            <c:forEach begin="0" end="50" step="10" var="i">
                <option >${i}</option>
            </c:forEach>
        </select>
        <br>
        <%--c:choose--%>
        <%
            request.setAttribute("score", 90);
            request.setAttribute("course", "java,jsp,spring,javaScript,mySQL");
            Map map = new HashMap();
            map.put("cn", "China");
            map.put("uk", "the Union Kindom");
            request.setAttribute("map", map);
        %>
        <c:choose>
            <c:when test="${score>90}">
                A
            </c:when>
            <c:when test="${score>80}">
                B
            </c:when>
            <c:when test="${score>70}">
                C
            </c:when>
            <c:when test="${score>60}">
                D
            </c:when>
            <c:otherwise>
                不及格
            </c:otherwise>
        </c:choose>
        <br>
       <%--分隔字符串--%>
        <c:forTokens items="${course}" delims="," var="c">
            课程：${c}<c:out value="${'<hr>'}" escapeXml="false"></c:out>
        </c:forTokens>
<br>

<c:forEach items="${map}" var="entry">
    ${entry.key}:${entry.value}<br>
</c:forEach>

</body>
</html>
