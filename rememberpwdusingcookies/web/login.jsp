<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%
        String basePath = request.getScheme()+"://"+request.getServerName()
                +":"+request.getServerPort()+request.getContextPath()+"/";
    %>

    <base href="<%=basePath%>">

    <script type="text/javascript">
        function checkUser(){
            document.getElementById("nameerror").innerHTML="";

            var username = document.getElementById("username").value;
            if(username == ""){
                document.getElementById("nameerror").innerHTML ="用户名不能为空";
                return false;
            }
            return true;
        }

        function checkPwd(){
            document.getElementById("pwderror").innerHTML = "";
            var password = document.getElementById("pwd").value;
            if(password.length<=6){
                document.getElementById("pwderror").innerHTML = "密码长度必须大于6";
                return false;
            }
            return true;
        }

        function checkForm(){
            var flag1 = checkUser();
            var flag2 = checkPwd();
            if(flag1 && flag2){
                return true;
            }else{
                return false;
            }

        }

    </script>
</head>
<body>
<h3>用户登录</h3>
<!--
action="http://127.0.0.1:8080/myservlet1/servlet/LoginServlet"
action="/myservlet1/servlet/LoginServlet0"

/myservlet1/admin/login.jsp
/myservlet1/servlet/LoginServlet0
/myservlet1/admin/LoginServlet0

页面中访问当前项目资源的路径建议使用相对路径，并且是相对于基准路径
-->
<%
    //1.从request拿出所有的会员卡
    Cookie [] cookies = request.getCookies();

    //2.找到所需要的会员卡
    String username ="";
    String password = "";
    String isChecked ="";//默认不记住
    if(cookies != null){
        for(int i=0;i<cookies.length;i++){
            if("username".equals(cookies[i].getName())){
                username = URLDecoder.decode(cookies[i].getValue(),"utf-8");
                isChecked =  "checked";
            }
            if("pwd".equals(cookies[i].getName())){
                password = cookies[i].getValue();
            }
        }
    }


    //3.出示会员卡

    //问题：LoginServlet创建了两个cookie，登录成功后，跳转到success.jsp,请问在success.jsp中能否得到两个cookie的值
       // 重定向：可以得到
       //转发：得不到


%>
<form action="servlet/UserServlet?method=login" method="post" onsubmit="return checkForm()">
    用户名 <input type="text" name="username" id="username" value="<%=username%>"  onblur="checkUser()">
    <span id="nameerror">
<%--        <%
          String nameerror = (String)request.getAttribute("nameerror") ;
          if(nameerror!=null){
              out.println(nameerror);
          }

        %>--%>
        ${requestScope.nameerror}
    </span>
    <br>
    密码 <input type="password" name="pwd" id="pwd"  value="<%=password%>" onblur="checkPwd()">
    <span id="pwderror">
        <%--<%
           String pwderror = (String) request.getAttribute("pwderror");
           if(pwderror !=null){
               out.println(pwderror);
           }

        %>--%>
        ${requestScope.pwderror}
    </span>
    <br>
    <input type="checkbox" value="yes" name="rememberme" <%=isChecked%>>10天免登录<br>
    <input type="submit"  value ="登录" ><br>
</form>
    <%--<%
        String error1 = (String) request.getAttribute("error");
        if(error1 ==null){
            error1 = "";
        }
        out.println(error1);
    %>--%>
${error1}

</body>
</html>