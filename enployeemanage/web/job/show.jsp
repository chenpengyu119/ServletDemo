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
    <title>查询</title>
    <style type="text/css">
        #show_title{
            /*margin: 0 auto;*/
            text-align: center;
        }
        hr{
            height: 1px;
            background-color: black;

        }
        #find_condition{
            /*border: 1px solid red;*/
            width: 300px;
            height: 30px;
            /*text-align: center;*/
            horiz-align: right;
            float: right;
        }
        #change_float{
            /*display: block;*/
            width: 900px;
            height: 30px;
            /*border: 1px solid blue;*/
        }
        #publish_job{
            text-align: center;
            font-size: 18px;
        }
        #show_tb{
            margin: 0 auto;
        }
        #div_publishBtn{
            text-align: center;
        }
    </style>
    <%
        String basePath = request.getScheme()+"://"+request.getServerName()
                +":"+request.getServerPort()+request.getContextPath()+"/";
    %>

    <base href="<%=basePath%>">

    <script type="text/javascript" src="/js/jquery-1.9.1.js">
    </script>
    <script type="text/javascript">
       function delJob(pn) {
           var isDel = window.confirm("确认删除？")
           if (isDel){
               location.href="servlet/TPositionServlet?method=del&pname="+pn;
           }
       }

    </script>

</head>
<body>
<h1 id="show_title">招聘系统企业版</h1>
<hr>
<div id="find_condition">

    <form action="servlet/JobServlet?method=find" method="post">
        <span id="find_title">按职位:</span>
    <input type="text" id="find_input" name="job_condition" value="${pname}"/>
    <input type="submit" id="condition_btn" value="查询">
    </form>
</div>
<div id="change_float"></div>
<hr>
<div id="div_publishBtn">
    <a href="servlet/TCompanyServlet?method=findCompany" id="publish_job">发布新的招聘信息</a>
</div>
<br>
<table border="1" id="show_tb">
    <tr>
    <th>职位名称</th>
    <th>公司名称</th>
    <th>职位月薪</th>
    <th>工作地点</th>
    <th>发布日期</th>
    <th>操作</th>

    </tr>
    <c:forEach items="${jobList}" var="job" varStatus="vs">
            <tr >
                <td >${job.pname}</td>
                <td >${job.cname}</td>
                <td >${job.minsal}-${job.maxsal}</td>
                <td >${job.location}</td>
                <td >${job.releasedate}</td>
                <td>
                    <a href="javaScript:delJob('${job.pname}');">删除</a>
                </td>
            </tr>
    </c:forEach>

</table>

</body>
</html>
