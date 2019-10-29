<%--
  Created by IntelliJ IDEA.
  User: pengyu
  Date: 2019/7/10
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上学堂主页</title>
</head>
<body>
     <%-- 这是jsp指令标签中的include指令，是静态包含，在转译时将文件内容加载到java文件中--%>
    <%@include file="header.jsp"%>
     <%--jsp脚本中的声明--%>
    <%! // 这里面都是成员，不能出现语句，只能是变量定义和方法
        int count = 0;
        void add(){
            count++;
        }
    %>

     <%-- 这是jsp脚本中的小脚本--%>
    <%
        add();
    %>

     <div style="height: 500px">
         <%--这是jsp脚本中的表达式--%>
         网站访问次数:<%=count%>
     </div>

     <%--这是jsp标签中的动作标签，这是动态包含，会在运行时调用包含文件的class文件--%>
    <jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
