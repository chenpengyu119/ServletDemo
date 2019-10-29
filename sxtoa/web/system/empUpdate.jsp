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
                        request.getServerPort()+                        //当前项目
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
    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">人事管理</a></li>
        <li><a href="#">修改员工信息</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form action="servlet/EmployeeServlet?method=update" method="post">
        <ul class="forminfo">
            <li>
                <label>用户名</label>
                <input name="empId" type="text" class="dfinput" value="${empUpdt.empId}" /><i>必须唯一，也可以根据真实姓名自动生成</i></li>
            <li>
            <li>
                <label>真实姓名</label>
                <input name="realName" type="text" class="dfinput" value="${empUpdt.realName}"/><i></i></li>
            <li>
                <label>性别</label><cite>
                <c:if test="${empUpdt.sex=='男'}">
                <input name="sex" type="radio" value="男" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="sex" type="radio" value="女" />女<i>也可以根据身份证号自动获取</i></cite>
                </c:if>
                <c:if test="${empUpdt.sex=='女'}">
                    <input name="sex" type="radio" value="男"  />男&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="sex" type="radio" value="女" checked="checked" />女<i>也可以根据身份证号自动获取</i></cite>
                </c:if>

            </li>
            <li>
                <label>出生日期</label>
                <input name="birthdate" type="text" class="dfinput" value="${empUpdt.birthdate}" onfocus="WdatePicker({skin:'whyGreen'})" readonly="readonly"/><i>也可以根据身份证号自动获取</i></li>
            <li>
            <li>
                <label>入职时间</label>
                <input name="hiredate" type="text" class="dfinput" value="${empUpdt.hiredate}" onfocus="WdatePicker({skin:'whyGreen'})" readonly="readonly" /><i></i></li>

            <li>
                <label>离职时间</label>
                <input name="leavedate" type="text" class="dfinput" value="${empUpdt.leavedate}" onfocus="WdatePicker({skin:'whyGreen'})" readonly="readonly" /><i></i></li>
            <li>
                <label>是否在职</label><cite>
                <c:if test="${empUpdt.onduty==1}">
                <input name="onduty" type="radio" value="1" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="onduty" type="radio" value="0" />否</cite>
                </c:if>
                <c:if test="${empUpdt.onduty==0}">
                    <input name="onduty" type="radio" value="1"  />是&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="onduty" type="radio" value="0" checked="checked"/>否</cite>
                </c:if>

            </li>
            <li>
                <label>所属部门<b>*</b></label>
                <div class="vocation">
                    <select class="select1" name="deptNo">
                        <c:forEach items="${deptList}" var="dept">
                            <c:if test="${dept.deptNo==empUpdt.deptNo}">
                                <option selected="selected" value="${dept.deptNo}">${dept.deptName}</option>
                            </c:if>
                            <c:if test="${dept.deptNo!=empUpdt.deptNo}">
                                <option value="${dept.deptNo}">${dept.deptName}</option>
                            </c:if>

                        </c:forEach>
                    </select>
                </div>

            </li>
            <li>
                <label>直接上级<b>*</b></label>
                <div class="vocation">
                    <select class="select1" name="mgrId">
                        <c:forEach items="${empList}" var="emp1">
                            <%--如果上级中一个人的id和当前用户领导id一致，则选中--%>
                            <c:if test="${emp1.empId==empUpdt.mgrId}">
                                <option selected="selected" value="${emp1.empId}">${emp1.realName}</option>
                            </c:if>
                            <c:if test="${emp1.empId!=empUpdt.mgrId}">
                                <option value="${emp1.empId}">${emp1.realName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                &nbsp;&nbsp;<input name="" type="text" class="dfinput"  placeholder="也可以在此输入首字母帮助显示"/></li>
            </li>
            <li>
                <label>联系方式</label>
                <input name="phone" type="text" class="dfinput" value="${empUpdt.phone}"/>
            </li>
            <li>
                <label>QQ号</label>
                <input name="qq" type="text" class="dfinput"value="${empUpdt.qq}" />
            </li>
            <li>
                <label>紧急联系人信息</label>
                <textarea name="emerContactPerson" cols="" rows="" class="textinput">${empUpdt.emerContactPerson}</textarea>
            </li>
            <li>
                <label>身份证号</label>
                <input name="idCard" type="text" class="dfinput" value="${empUpdt.idCard}"/>
            </li>
            <li>
                <label>&nbsp;</label>
                <input name="" type="submit" class="btn" value="确认保存" />
            </li>
        </ul>

    </form>


</div>

</body>

</html>