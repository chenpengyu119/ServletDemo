<%--
  Created by IntelliJ IDEA.
  User: pengyu
  Date: 2019/7/19
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>MyAjax</title>
    <script type="text/javascript"src="/js/jquery-1.8.2.js"></script>
    <script type="text/javascript">
      $(function () {
          $("#uname").bind("blur",function () {
              $.ajax({
                 type:"GET",
                 url:"/servlet/LoginServlet",
                 dataType:"text",
                 data:{"uname":$("#uname").val()},
                 success:function (result) {
                     $("#sp").html(result);
                 } ,
              });
          })
      })

    </script>
    <script type="text/javascript">

      function checkname() {
          var uname = document.getElementById("uname").value;
          // 创建对象
          var ajax;
          if (window.XMLHttpRequest!=null){
              ajax = new XMLHttpRequest();
          } else if(window.ActiveXObject!=null){
              ajax = newVMObject("Msxm12.XMLHTTP");
          }
          // 重写onreadystatechange函数
          ajax.onreadystatechange = function (ev) {
              if (ajax.readyState==4){
                  if (ajax.status==200){
                      var res = ajax.responseText;
                      var sp = document.getElementById("sp");
                      sp.innerHTML=res;
                  }
              }
          }
          // 发送请求
          ajax.open("get","/servlet/LoginServlet?uname="+uname);
          ajax.send(null);
      }
    </script>
  </head>
  <body>

  <form action="/servlet/LoginServlet" method="post">
    用户名：<input type="text" id="uname" name="uname" ><span id="sp"></span><br>
    密码：<input type="password" name="password" id="password"><br>
    <input type="submit" value="登录">

  </form>
  </body>
</html>
