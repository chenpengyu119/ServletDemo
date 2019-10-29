<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
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
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#signin").bind("click",function () {
                $.ajax({
                    url:"/sxtoa/servlet/DutyServlet?method=signin",
                    success:function (data) {
                        $("#msgRes").html(data);
                    }
                })
            })
            // 签退
            $("#signout").bind("click",function () {
                $.ajax({
                    url:"/sxtoa/servlet/DutyServlet?method=signout",
                    success:function (data) {
                        $("#msgRes").html(data);
                    }
                })
            })
        })

    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">考勤管理</a></li>
        <li><a href="#">签到签退</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>

    <ul class="forminfo">
        <li><label>&nbsp;</label><input id="signin" name="signin" type="button" class="btn" value="签到"/> 每天签到一次，不可重复签到</li>
        <li><label>&nbsp;</label></li>
        <li><label>&nbsp;</label></li>
        <li><label>&nbsp;</label><input id="signout"  name="signout" type="button" class="btn" value="签退"/>可重复签退，以最后一次签退为准</li>
    </ul>
    <span id="msgRes"></span>

</div>


</body>

</html>

