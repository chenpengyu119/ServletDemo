<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>审核报销单</title>
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
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript">
        function submitForm(){

        }

        function addAudit() {
            var res = $("#result input:checked").val();
            var auditdesc = $("#auditdesc").val();
            var expId = $("#expId").val();
            var totalAmount =$("#totalAmount").val();
            var empId = $("#empId").val();
            console.info(res+"  "+auditdesc+"  "+expId+"  "+totalAmount);
           /* window.close();*/
            $.ajax({
                url:"servlet/ExpenseServlet?method=addAudit",
                data:{
                    result:res,
                    auditDesc:auditdesc,
                    expId:expId,
                    totalAmount:totalAmount,
                    empId:empId
                },
                success:function (data) {
                    window.close();
                    window.opener.location.reload();
                },
                error:function (xhr) {
                    alert(xhr.status+xhr.statusText);
                }
            });

        }
    </script>
</head>

<body>

<div class="formtitle"><span>审核报销单</span></div>
<form action="#">
    <ul class="forminfo">

        <li id="result">
            <label>审核结果</label>
            <input name="result" type="radio"  value="通过" />通过
            <input name="result" type="radio" value="拒绝"/>拒绝
            <input name="result" type="radio" value="打回"/>打回
            <input value="${param.expId}" id="expId" type="hidden"/>
            <input value="${param.totalAmount}" id="totalAmount" type="hidden"/>
            <input value="${param.empId}" id="empId" type="hidden"/>
        </li>
        <li>
            <label>审核意见</label>
            <input name="auditdesc" id="auditdesc" type="text" class="dfinput" />
        </li>
        <li>
            <label>&nbsp;</label>
            <input name="" type="button" id="savebtn" class="btn" value="确认保存" onclick="addAudit()" />
        </li>
    </ul>
</form>
</body>

</html>
