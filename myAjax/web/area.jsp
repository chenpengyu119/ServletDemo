<%--
  Created by IntelliJ IDEA.
  User: pengyu
  Date: 2019/7/19
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询区域信息</title>
    <style type="text/css">
        select{
            width:300px;
            height:30px;
        }
        #showdiv{
            width:920px;
            margin:auto;
            margin-top:200px;
        }
    </style>
    <script type="text/javascript" src="/js/jquery-1.8.2.js"></script>
    <script type="text/javascript">
        $(function () {
            $.ajax({
                type:"POST",
                url:"/servlet/AreaServlet",
                dataType:"text",
                data:{"parentId":0},
                success:function (data) {
                    // 将字符串转成json对象
                    eval("var jsonObj="+data);
                    var str="";
                    for (var i=0;i<jsonObj.length;i++){
                        str += "<option value="+jsonObj[i].areaId+">"+jsonObj[i].areaName+"</option>"+"<br>"
                    }
                    $("#province").html(str);
                    getCity();
                }
            })
        });
        function getCity() {
            $.ajax({
                type:"POST",
                url:"/servlet/AreaServlet",
                dataType:"text",
                data:{"parentId":$("#province").val()},
                success:function (data) {
                    // 将字符串转成json对象
                    eval("var jsonObj="+data);
                    var str="";
                    for (var i=0;i<jsonObj.length;i++){
                        str += "<option  value="+jsonObj[i].areaId+">"+jsonObj[i].areaName+"</option>"+"<br>";
                    }
                    $("#city").html(str);
                    getCounty();
                }
            });
        }

        function getCounty() {
            $.ajax({
                type:"POST",
                url:"/servlet/AreaServlet",
                dataType:"text",
                data:{"parentId":$("#city").val()},
                success:function (data) {
                    // 将字符串转成json对象
                    eval("var jsonObj="+data);
                    var str = "";
                    for (var i=0;i<jsonObj.length;i++){
                        str += "<option  value="+jsonObj[i].areaId+">"+jsonObj[i].areaName+"</option>"+"<br> ";
                    }
                    $("#county").html(str);
                }
            });
        }

    </script>

</head>
<body>
<div id="showdiv">
    <select name="province" id="province" onchange="getCity()">
        
    </select>
    <select name="city" id="city" onchange="getCounty()">

    </select>
    <select name="county" id="county">

    </select>
</div>
</body>
</html>
