<%--
  Created by IntelliJ IDEA.
  User: pengyu
  Date: 2019/7/15
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
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

    <style type="text/css">
        #registererror{
            color: red;
        }
    </style>
    <script type="text/javascript" src="../remember/js/jquery-1.9.1.js"></script>
    <script type="text/javascript">

        function checkuname(){
            var uname = $("#uname").val();
            var unamespan = $("#unameerror");
            unamespan.empty();

            if (uname==""){
                unamespan.html("用户名不能为空");
                return false;
            }else if (uname.length > 10){
                unamespan.html("用户名长度不能超过10");
                return false;
            }
        }

        function checkpwd(){
            var pwd = $("#pwd").val();
            var pwderror = $("#pwderror");
            pwderror.empty();

            var pwdregex = /^[A-Z][a-zA-Z\d@_]{5,9}$/;
            if (pwd==""){
                pwderror.html("密码不能为空");
                return false;
            }else if (!pwdregex.test(pwd)){
                pwderror.html("密码只能为6-10位字母数字或@,_,必须以大写字母开头");
                return false;
            }
        }

        function checkConfirmPwd(){
            var pwd = $("#pwd").val();
            var confirmPwd = $("#confirmPwd").val();
            var confirmPwdError = $("#isSame");
            confirmPwdError.empty();
            if (confirmPwd == null || confirmPwd != pwd){
                confirmPwdError.html("两次密码不一致");
                return false;
            }

        }

        function checkAge(){
            var age = $("#age").val();
            var check = $("#checkAge");
            check.empty();

            if (isNaN(age)){
                check.html("年龄必须是数字");
                return false;
            } else if(age <=0 || age >180){
                check.html("年龄必须在1-180之间");
                return false;
            }

        }

        $(function () {
            // 用户名 10
            $("#uname").bind("mouseout",(function () {
                checkuname();
            }))
            $("#pwd").bind("mouseout",(function () {
                checkpwd();
            }))
            $("#confirmPwd").bind("mouseout",(function () {
                checkConfirmPwd();
            }))
            $("#age").bind("mouseout",(function () {
                checkAge();
            }))
        })

        function checkForm() {
            return checkuname()&&checkpwd()&&checkConfirmPwd()&&checkAge();
        }

    </script>
</head>
<body>
<form method="post" action="servlet/UserServlet?method=register" onsubmit="return checkForm()">
    <table border="0px">
        <tr>
            <th colspan="2">用户注册</th>
        </tr>
        <tr>
            <td>用户名：</td>
            <td>
                <input type="text" name="uname" id="uname"  />
            </td>
            <td>
                <span id="unameerror"></span>
            </td>
        </tr>

        <tr>
            <td>真实姓名：</td>
            <td>
                <input type="text" name="realname" id="realname"  />
            </td>
        </tr>

        <tr>
            <td>密码：</td>
            <td>
                <input type="password" name="pwd" id="pwd" value="" />
            </td>
            <td>
                <span id="pwderror"></span>
            </td>
        </tr>
        <tr>
            <td>确认密码：</td>
            <td>
                <input type="password" name="confirmPwd" id="confirmPwd" value="" />
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <span id="isSame"></span>
            </td>

        </tr>

        <tr>
            <td>年龄：</td>
            <td>
                <input type="number" name="age" id="age" value="" />
            </td>
            <td colspan="3">
                <span id="checkAge"></span>
            </td>
        </tr>
        <tr>
            <td style="vertical-align: top;">兴趣：</td>
            <td colspan="2">
                <div class="mui-input-row mui-checkbox ">
                    <label>音乐</label>
                    <input name="hobby" type="checkbox"  value="music"  >
                    <label>绘画</label>
                    <input name="hobby" type="checkbox" value="art"> <br>
                    <label>运动</label>
                    <input name="hobby" type="checkbox" value="sports" >
                    <label>读书</label>
                    <input name="hobby" type="checkbox" value="read"> <br>
                    <label>其他</label>
                    <%--<input name="hobby" type="text" id="others" >--%>
                    <textarea name="hobby" rows="1" cols="16" style= "overflow:hidden; resize:none; ">

										</textarea>
                </div>

            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div style="text-align: center">
                    <input type="submit" value="注册" style="margin-right:15px ">
                    <input type="reset" value="重置" />
                </div>
            </td>

        </tr>
        <tr>
            <td colspan="3">
               <%-- <%
                    Object rstObj = request.getAttribute("registererror");

                    String registererror = "";
                    if (rstObj!=null){
                        registererror = (String)rstObj;
                    }
                %>--%>
                <span id="registererror">${requestScope.registererror}</span>
            </td>

        </tr>
    </table>



</form>
</body>
</html>
