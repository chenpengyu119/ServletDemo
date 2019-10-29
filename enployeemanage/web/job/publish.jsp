<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pengyu
  Date: 2019/7/18
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%
        String basePath = request.getScheme()+"://"+request.getServerName()
                +":"+request.getServerPort()+request.getContextPath()+"/";
    %>

    <base href="<%=basePath%>">
    <title>发布</title>
    <style type="text/css">
        #publis_title{
            text-align: center;
        }
        #publish_div{
            /* border: 1px solid red;*/
            width: 300px;
            height: 170px;
            display: block;
            horiz-align: center;
            margin:0 auto
        }
        span{
            color: red;
        }
    </style>
    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript">

        function checkName(){
            var sp = $("#position_name_err");
            sp.empty();
            var name_val = $("#position_name").val();
            if (name_val=== null ||""===name_val){
                sp.html("职位名不得为空")
            }
        }
        function checkMin(){
            var sp = $("#minSal_err");
            sp.empty();
            var name_val = $("#minSal").val();
            if (name_val=== null ||""===name_val){
                sp.html("最低工资不得为空")
            }
        }
        function checkMax(){
            var sp = $("#maxSal_err");
            sp.empty();
            var name_val = $("#maxSal").val();
            if (name_val=== null ||""===name_val){
                sp.html("最高工资不得为空")
            }
        }




        $(function () {
            $("#position_name").bind("blur",function () {
                checkName();
            });
            $("#minSal").bind("blur",function () {
                checkMin();
            });
            $("#maxSal").bind("blur",function () {
                checkMax();
            })
        })

    </script>
</head>
<body>
<hr id="top_hr">
<h1 id="publis_title">发布职位招聘信息</h1>
<hr id="hr_center">
<div id="publish_div">
    <form action="servlet/TPositionServlet?method=publish" method="post">
        <table width="400px" id="publish_tb">
            <tr>
                <td>职位名称:</td>
                <td>
                    <input type="text" id="position_name" name="position_name">
                </td>
                <td>
                    <span id="position_name_err"></span>
                </td>
            </tr>
            <tr>
                <td>最低薪水:</td>
                <td>
                    <input type="text" id="minSal" name="minSal">
                </td>
                <td>
                    <span id="minSal_err"></span>
                </td>
            </tr>
            <tr>
                <td>最高薪水:</td>
                <td>
                    <input type="text" id="maxSal" name="maxSal">
                </td>
                <td>
                    <span id="maxSal_err"></span>
                </td>
            </tr>
            <tr>
                <td>发布公司:</td>
                <td>
                    <select name="company">
                        <c:forEach items="${companyList}" var="company">
                            <option id="company" name="company1">${company.cname}</option>
                        </c:forEach>

                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <input type="submit" value="发布">
                </td>
            </tr>
        </table>
    </form>

</div>

</body>
</html>
