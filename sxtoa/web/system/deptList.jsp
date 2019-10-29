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
        $(document).ready(function(){
            $(".click").click(function(){
                $(".tip").fadeIn(200);
            });

            $(".tiptop a").click(function(){
                $(".tip").fadeOut(200);
            });

            $(".sure").click(function(){
                $(".tip").fadeOut(100);
            });

            $(".cancel").click(function(){
                $(".tip").fadeOut(100);
            });

        });
        function del(deptNo) {
            var flag = window.confirm("您是否确认删除");
            if (flag){
                location.href="servlet/DepartmentServlet?method=delete&deptNo="+deptNo;
            }
        }
    </script>


</head>


<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">人事管理</a></li>
        <li><a href="#">部门管理</a></li>
    </ul>
</div>

<div class="rightinfo">


    <div class="formtitle1"><span>部门列表</span></div>

    <table class="tablelist" >
        <thead>
        <tr>
            <th><input name="" type="checkbox" value="" checked="checked"/></th>
            <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
            <th>部门名称</th>
            <th>办公地点</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${deptList}" var="dept">
            <tr>
                <td><input name="" type="checkbox" value="" /></td>
                <td>${dept.deptNo}</td>
                <td>${dept.deptName}</td>
                <td>${dept.location}</td>
                <td><a href="servlet/DepartmentServlet?method=findById&deptNo=${dept.deptNo}" class="tablelink">修改</a> &nbsp;&nbsp;&nbsp;&nbsp;  <a href="javaScript:del(${dept.deptNo})" class="tablelink"> 删除</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    ${error}

</div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

</body>

</html>

