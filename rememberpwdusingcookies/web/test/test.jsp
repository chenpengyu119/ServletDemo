<%--
  Created by IntelliJ IDEA.
  User: pengyu
  Date: 2019/7/17
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试</title>
</head>

<body>
<%--<%
    request.setAttribute("ins", 10);
%>--%>
<input type="text" id="in" value="1000">
<input type="button" onclick="clear()" value="清空">
<a href="#" id="aaa">按钮</a>
<script type="text/javascript">
    function clear() {
        alert("hhhhhh")
        var  c = document.getElementById("in").value;
        alert(c);
    }
    window.onload = function (ev) {
        alert("shfhisdjgnishgi")
        document.getElementById("aaa").click(function () {
            alert("button");
        })
    }

</script>
</body>
</html>
