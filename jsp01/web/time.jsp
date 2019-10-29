<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: pengyu
  Date: 2019/7/10
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>获取时间</title>
    <script type="text/javascript">
      window.onload = function () {
          /*客户端时间*/
          var time = new Date();
          document.getElementById("show").innerHTML="客户端时间："+time.toDateString();
      }

    </script>
  </head>
  <body>


       <%--服务器时间--%>
       <%
         System.out.println("1.服务器端时间："+new Date().toString());
         out.println("2.服务器端时间："+new Date().toString());

         Thread.sleep(3000);
       %>
       <br>
       服务器端时间（通过表达式编写）<%= new Date().toString()%>

       <div id="show"></div>
  </body>
</html>
