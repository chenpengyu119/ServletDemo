<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
   <div>
     <h5>店铺服装管理</h5><br>
     <span>
       <form method="post" action="/clothesmgr/servlet/ClothesServlet?method=findByName">
         <input type="text" name="cname">
         <input type="submit" value="查询">
         <a href="/clothesmgr/add.jsp">添加</a>

       </form>
     </span>
   </div>
  <hr>
  <div>
    <h5 align="center">服装列表</h5>
    <table border="1" width="700px">
      <tr>
        <th>序号</th>
        <th>服装名称</th>
        <th>品牌</th>
        <th>性别</th>
        <th>操作</th>
      </tr>

      <c:forEach items="${clothesList}" var="clothes">
        <tr>
          <td>${clothes.cid}</td>
          <td>${clothes.cname}</td>
          <td>${clothes.ebrand}</td>
          <td>${clothes.cgender}</td>
          <td>
            <a href="/clothesmgr/servlet/DetailServlet?method=findById&cid=${clothes.cid}&cname=${clothes.cname}&ebrand=${clothes.ebrand}&cgender=${clothes.cgender}">详情</a>
          </td>
        </tr>


      </c:forEach>


    </table>

  </div>
  </body>
</html>
