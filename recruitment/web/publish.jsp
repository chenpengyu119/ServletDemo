<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>发布招聘信息</title>
    <style type="text/css">
        span{
            color: red;
        }
    </style>
    <script type="text/javascript">

        function checkpname() {
            var val = document.getElementById("pname").value;
            if (val==""){
                document.getElementById("pnametip").innerHTML="职位名不得为空";
                return false;
            }else {
                document.getElementById("pnametip").innerHTML=""
            }
            return true;
        }
        function checkminsal() {
            var val = document.getElementById("minsal").value;
            if (val==""){
                document.getElementById("minsaltip").innerHTML="最低薪水不得为空";
                return false;
            }else {
                document.getElementById("minsaltip").innerHTML=""
            }
            return true;
        }
        function checkmaxsal() {
            var val = document.getElementById("maxsal").value;
            if (val==""){
                document.getElementById("maxsaltip").innerHTML="最高薪水不得为空";
                return false;
            }else {
                document.getElementById("maxsaltip").innerHTML=""
            }
            return true;
        }
        function checkform1() {
            var check1 = checkmaxsal();
            var check2 = checkminsal();
            var check3 =  checkpname();
            if (check1&&check2&&check3){
                return true;
            }else {
                return false;
            }
        }


    </script>
</head>
<body>
<h3 align="center">发布招聘信息</h3>
<hr>
<div>
    <form method="post" action="/servlet/PositionServlet?method=publish" onsubmit="return checkform1()">
        <table>
            <tr>
                <td>职位名称：</td>
                <td>
                    <input type="text" name="pname" id="pname" onblur="checkpname()">
                </td>
                <td>
                    <span id="pnametip"></span>
                </td>
            </tr>
            <tr>
                <td>
                    最低薪水：
                </td>
                <td>
                    <input type="text" name="minsal" id="minsal" onblur="checkminsal()">
                </td>
                <td>
                    <span id="minsaltip"></span>
                </td>
            </tr>
            <tr>
                <td>最高薪水：</td>
                <td>
                    <input type="text" name="maxsal" id="maxsal" onblur="checkmaxsal()">
                </td>
                <td>
                    <span id="maxsaltip"></span>
                </td>
            </tr>
            <tr>
                <td>发布公司：</td>
                <td colspan="10">
                    <select name="company">
                        <c:forEach items="${companyList}" var="comp">
                            <option value="${comp.cid}">${comp.cname}</option>
                        </c:forEach>
                    </select>
                </td>
                <td></td>
            </tr>
            <tr>
                <td colspan="10">
                    <input type="submit" value="发布" >
                </td>

            </tr>
        </table>
    </form>


</div>

${error}
</body>
</html>
