<%--
  Created by IntelliJ IDEA.
  User: pengyu
  Date: 2019/7/19
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取省份</title>
    <script type="text/javascript" src="/js/jquery-1.8.2.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#code").bind("blur",function () {
                $.ajax({
                    type:"GET",
                    url:"/servlet/CodeServlet",
                    dataType:"text",
                    data:{"code":$("#code").val()},
                    error:function (xhr) {
                        alert(xhr.statusText+":"+xhr.status);
                    },
                    success:function (res) {
                        var arr = res.split(",");
                        $("#province").val(arr[0]);
                        $("#city").val(arr[1]);
                    }
                })
            })
        })
    </script>
</head>
<body>
<form action="">
    区号<input type="text" name="code" id="code" ><br>
    省份<input type="text" name="province" id="province" ><br>
    城市<input type="text" name="city" id="city"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
