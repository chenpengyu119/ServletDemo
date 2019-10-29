<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加</title>
    <script type="text/javascript">

        function checkcname() {
            var val = document.getElementById("cname").value;
            if (val==""){
                document.getElementById("cnametip").innerHTML="不能为空"
                return false;
            }else {
                document.getElementById("cnametip").innerHTML="";
                return true;
            }
        }

        function checkebrand() {
            var val = document.getElementById("ebrand").value;
            if (val==""){
                document.getElementById("ebrandtip").innerHTML="不能为空"
                return false;
            }else {
                document.getElementById("ebrandtip").innerHTML="";
                return true;
            }
        }

        function checkdprice() {
            var val = document.getElementById("dprice").value;
            if (val==""){
                document.getElementById("dpricetip").innerHTML="不能为空"
                return false;
            }else {
                document.getElementById("dpricetip").innerHTML="";
                return true;
            }
        }
        function checkform() {
            if (checkdprice()&&checkebrand()&&checkcname()){
                return true;
            } else {
                return false;
            }
        }



    </script>
</head>
<body>
<hr>
<div>

    <h5>新增服装</h5>
    <form method="post" action="/clothesmgr/servlet/DetailServlet?method=add" onsubmit="return checkform()">
        <table border="1" width="700px">
            <tr>
                <td>服装名：</td>
                <td>
                    <input type="text" name="cname" id="cname" onblur="checkcname()">
                </td>
                <td><span id="cnametip"></span> </td>
            </tr>
            <tr>
                <td>品牌：</td>
                <td>
                    <input type="text" name="ebrand" id="ebrand" onblur="checkebrand()">
                </td>
                <td><span id="ebrandtip"></span> </td>
            </tr>
            <tr>
                <td>男/女装：</td>
                <td>
                    <select name="cgender">
                        <option value="男装">男装</option>
                        <option value="女装">女装</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>尺数：</td>
                <td>
                    <input type="text" name="dsize" id="dsize">
                </td>
                <td><span id="dsizetip"></span> </td>
            </tr>
            <tr>
                <td>颜色：</td>
                <td>
                    <input type="text" name="dcolor" id="dcolor">
                </td>
                <td><span id="dcolortip"></span></td>
            </tr>
            <tr>
                <td>服装价格：</td>
                <td>
                    <input type="text" name="dprice" id="dprice" onblur="checkdprice()">
                </td>
                <td><span id="dpricetip"></span></td>
            </tr>
            <tr>
                <td colspan="10">
                    <input type="submit" value="添加">
                </td>

            </tr>


        </table>
    </form>


</div>

</body>
</html>
