<%@ page import="com.bjsxt.entity.User" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>show.jsp</title>
    <style type="text/css">
        .in{
            width: 80px;
        }

        </style>
    <%
        String basePath = request.getScheme()+"://"+request.getServerName()
                +":"+request.getServerPort()+request.getContextPath()+"/";
    %>

    <base href="<%=basePath%>">
    <script type="text/javascript">
        function clear1() {
            document.getElementById("userId").value = "";
            document.getElementById("minAge").value = "";
            document.getElementById("sub").click();

        }
    </script>
</head>
<body>
<%
    HttpSession session1 = request.getSession();
    Object obj =session1.getAttribute("user");
    User user = null;
    String uname = "";
    if (obj!=null) {
        user = (User)obj;
        uname = user.getRealname();
    }

%>
show.jsp
<%= uname%>
<%=session1.getId()%>

<form method="post" action="servlet/UserServlet?method=find">

    <input type="text" class="in" id="userId" name="userId" value="${userId}">姓名 <span style="width: 30px"></span>
    <input class="in" type="number" id="minAge" name="minAge" value="${minAge}"> 年龄<span style="width: 30px"></span>
    <input type="submit" value="查询" id="sub"> <input type="button" value="重置" onclick="clear1()">


</form>

<table border="1">

    <tr>
        <th>用户名</th>
        <th>真实姓名</th>
        <th>年龄</th>
        <th>兴趣</th>
        <th>入职时间</th>
        <th>vs.index</th>
        <th>vs.count</th>
    </tr>
    <%--<%
        request.setAttribute("userList", null);
    %>--%>
    <c:if test="${empty userList}">
        <tr>
            <td colspan="10">
                一个人都没有
            </td>
        </tr>

    </c:if>
   <c:if test="${not empty userList}">
     <%--求平均值--%>
       <c:set var="sum" value="0"></c:set> <%--相当于setAttribute("sum",0)--%>
       <c:set var="count" value="0"></c:set>

       <c:forEach items="${userList}" var="user" varStatus="vs">
           <c:if test="${vs.index%2==0}">
               <tr style="background: yellow">
                   <td >${user.userId}</td>
                   <td >${user.realname}</td>
                   <td >${user.age}</td>
                   <td >${user.hobby}</td>
                   <td >${user.enterDate}</td>
                   <td>${vs.index}</td>
                   <td>${vs.count}</td>
               </tr>
           </c:if>
           <c:if test="${vs.index%2!=0}">
               <tr >
                   <td >${user.userId}</td>
                   <td >${user.realname}</td>
                   <td >${user.age}</td>
                   <td >${user.hobby}</td>
                   <td >${user.enterDate}</td>
                   <td>${vs.index}</td>
                   <td>${vs.count}</td>

               </tr>
           </c:if>
           <c:set var="sum" value="${sum+user.age}"></c:set>
           <c:set var="count" value="${count+1}"></c:set>
       </c:forEach>
       <tr>
           <td colspan="10">
               总人数：${count},平均年龄： <fmt:formatNumber value="${sum/count}" pattern="##.#"></fmt:formatNumber>
           </td>
       </tr>


   </c:if>
    
    
</table>





</body>
</html>
