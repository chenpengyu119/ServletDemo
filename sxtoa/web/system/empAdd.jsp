<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
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
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="css/select.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="js/select-ui.min.js"></script>
    <script type="text/javascript" src="editor/kindeditor.js"></script>
    <script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(document).ready(function(e) {
            $(".select1").uedSelect({
                width : 345
            });
        });
        // ajax请求
        function getDept(){
            // 所属部门
            $.ajax({
                type:"POST",
                url:"/sxtoa/servlet/EmployeeServlet?method=getDept",
                dataType:"text",
                success:function (data) {
                    console.info(data);
                    eval("var jsonObj="+data);
                    var sel = $("#deptNo");
                    var str = "";
                    for (var i=0;jsonObj.length>i;i++) {
                        str += "<option value=" + jsonObj[i].deptNo + ">" + jsonObj[i].deptName + "</option><br>";
                    }
                    sel.html(str);
                  // sel[0].selectedIndex = -1;
                }
            })
        }
        function getPos(){
            // 岗位
            $.ajax({
                type:"POST",
                url:"/sxtoa/servlet/EmployeeServlet?method=getPos",
                dataType:"text",
                success:function (data) {
                    console.info(data);
                    eval("var jsonObj="+data);
                    var sel = $("#posId");
                    var str = "";
                    for (var i=0;jsonObj.length>i;i++) {
                        str += "<option value=" + jsonObj[i].posId + ">" + jsonObj[i].pname + "</option><br>";
                    }
                    sel.html(str);
                    // sel[0].selectedIndex = -1;
                }
            })
        }

        function getMgr(){
            // 上级
            $.ajax({
                type:"POST",
                url:"/sxtoa/servlet/EmployeeServlet?method=getMgr",
                dataType:"text",
                success:function (data) {
                    console.info(data);
                    eval("var jsonObj="+data);
                    var sel = $("#mgrId");
                    var str = "";
                    for (var i=0;jsonObj.length>i;i++) {
                        str += "<option " +
                            "value=" + jsonObj[i].empId + ">" + jsonObj[i].realName + "</option><br>";
                    }
                    sel.html(str);
                    // sel[0].selectedIndex = -1;
                }
            })
        }

        $(function () {
            getDept();
            getPos();
            getMgr();
        })

    </script>
    <script type="text/javascript">
        KE.show({id:"ecp",width:"800px",height:"300px"});
    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">人事管理</a></li>
        <li><a href="#">添加员工</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form action="servlet/EmployeeServlet?method=add" method="post">

        <ul class="forminfo">
            <li>
                <label>用户名</label>
                <input name="empId" type="text" class="dfinput" /></li>
            <li>
            <li>
                <label>真实姓名</label>
                <input name="realName" type="text" class="dfinput" /><i></i></li>
            <li>
                <label>密码</label>
                <input name="password" type="password" class="dfinput" /><i></i></li>
            <li>
                <label >性别</label><cite>
                <input name="sex" type="radio" value="男" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="sex" type="radio" value="女" />女<i>也可以根据身份证号自动获取</i></cite>

            </li>
            <li>
                <label>出生日期</label>
                <input name="birthdate" type="text" class="dfinput" onfocus="WdatePicker({skin:'whyGreen',lang:'en'})" /><i>也可以根据身份证号自动获取</i></li>
            <li>
            <li>
                <label>入职时间</label>
                <input name="hiredate" type="text" class="dfinput" onfocus="WdatePicker()"/><i></i></li>

            <li>
                <label>是否在职</label><cite>
                <input name="onduty" type="radio" value="1" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="onduty" type="radio" value="0" />否</cite>
            </li>
            <li>
                <label>员工类型</label><cite>
                <input name="empType" type="radio" value="1" checked="checked" />基层员工&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="empType" type="radio" value="2" />各级管理人员</cite>
            </li>
            <li>
                <label>所属部门<b>*</b></label>
                <div class="vocation">
                    <select class="select1" name="deptNo" id="deptNo">
                    </select>
                </div>

            </li>
            <li>
                <label>从事岗位<b>*</b></label>
                <div class="vocation">
                    <select class="select1" name="posId" id="posId">
                    </select>
                </div>

            </li>
            <li>
                <label>直接上级<b>*</b></label>
                <div class="vocation">
                <select class="select1" name="mgrId" id="mgrId">
                </select>
            </div>
                &nbsp;&nbsp;<input name="" type="text" class="dfinput"  placeholder="也可以在此输入首字母帮助显示"/></li>
            </li>
            <li>
                <label>联系方式</label>
                <input name="phone" type="text" class="dfinput" />
            </li>
            <li>
                <label>QQ号</label>
                <input name="qq" type="text" class="dfinput" />
            </li>
            <li>
                <label>紧急联系人信息</label>
                <textarea name="emerContactPerson" cols="" rows="" id="ecp" class="textinput"></textarea>
            </li>
            <li>
                <label>身份证号</label>
                <input name="idCard" type="text" class="dfinput" />
            </li>
            <li>
                <label>&nbsp;</label>
                <input name="" type="submit" class="btn" value="确认保存" />
            </li>
        </ul>
        <span style="color: red;font-size: 18px"> ${error}</span>
    </form>

</div>

</body>

</html>
