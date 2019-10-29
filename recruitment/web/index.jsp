<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>首页</title>
    <script type="text/javascript">

      function confirmdel(pid) {
          var flag = window.confirm("请问您是否确认删除？")
          if (flag){

              window.location = "/servlet/PositionServlet?method=del&pid="+pid;
          }

      }


    </script>
  </head>
  <body>
  <h1 align="center">招聘系统企业版</h1>
  <hr>
  <div>
    <form action="/servlet/PositionServlet?method=findByPname" method="post">
      <span>
    按职位：<input type="text" name="pname"><input value="查询" type="submit">
    </span>
    </form>
  </div>
  <hr>
  <a href="/servlet/CompanyServlet?method=toPublish">发布新的招聘信息</a>

  <table border="1" width="700px">
    <tr>
      <th>职位名称</th>
      <th>公司名称</th>
      <th>职位月薪</th>
      <th>工作地点</th>
      <th>发布日期</th>
      <th>操作</th>
    </tr>

    <c:forEach items="${posList}" var="pos">
      <tr>
        <td>${pos.pname}</td>
        <td>${pos.company.cname}</td>
        <td>${pos.minsal}-${pos.maxsal}</td>
        <td>${pos.company.location}</td>
        <td>${pos.releasedate}</td>
        <td>
          <a href="javascript:confirmdel(${pos.pid})" disabled>删除</a>
        </td>
      </tr>

    </c:forEach>



  </table>
  ${error}
  </body>
</html>
