<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        $(document).ready(function() {
            $(".click").click(function() {
                $(".tip").fadeIn(200);
            });
            $(".tiptop a").click(function() {
                $(".tip").fadeOut(200);
            });
            $(".sure").click(function() {
                $(".tip").fadeOut(100);
            });
            $(".cancel").click(function() {
                $(".tip").fadeOut(100);
            });
        });
        $(function () {
            $("#retbt").bind("click",function () {
                history.go(-1);
            })
        })
    </script>

</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">报销管理</a></li>
        <li><a href="#">查看具体报销项</a></li>
    </ul>
</div>

<div class="rightinfo">

    <div class="formtitle1"><span>具体报销项</span></div>

    <table class="tablelist">
        <thead>
        <tr>
            <th>
                <input name="" type="checkbox" value="" checked="checked" />
            </th>
            <th>报销项编号<i class="sort"><img src="images/px.gif" /></i></th>
            <th>报销项类型</th>
            <th>报销项金额</th>
            <th>报销项说明</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${itemList}" var="item">
            <tr>
                <td>
                    <input name="" type="checkbox" value="" />
                </td>
                <td>${item.itemId}</td>
                <c:if test="${item.type=='t1'}">
                    <td>通信费用</td>
                </c:if>
                <c:if test="${item.type=='t2'}">
                    <td>办公室耗材</td>
                </c:if>
                <c:if test="${item.type=='t3'}">
                    <td>住宿费用</td>
                </c:if>
                <c:if test="${item.type=='t4'}">
                    <td>房租水电</td>
                </c:if>
                <c:if test="${item.type=='t5'}">
                    <td>其他</td>
                </c:if>


                <td>${item.amount}</td>
                <td>${item.itemDesc}</td>

            </tr>

        </c:forEach>

        </tbody>
    </table>

</div>
<input name="" type="button" class="btn" id="retbt" value="返回" />
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

</body>

</html>