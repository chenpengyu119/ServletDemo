<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
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
    <script type="text/javascript">
        /**
         * 改变url
         */
        function change(index,size) {
            window.location.href = "servlet/ShowAllServlet?index="+index+"&size="+size;
        }

        /**
         * 跳转到指定页码
         */
        function changeIndex(size) {
            var index = document.getElementById("indexn").value;
            change(index,size);
        }

        /**
         * 改变每页显示的条数
         */
        function changeSize(size,index) {
            change(index,size);

        }
    </script>
</head>
<body>

<hr>
<form action="servlet/ShowAllServlet" method="post" id="form1" name="form1">
    <table align="center">
        <tr>
            <td>姓名</td>
            <td>
                <input type="text" name="name" value="${name}">
            </td>
            <td>分数>=</td>
            <td><input type="text" name="minScore" value="${minScore}"></td>
            <td><input type="submit" value="提交"></td>
        </tr>
    </table>
</form>
<hr>

<!-- 显示所有学生   /stumanager/    -->
<table align="center" border="1" width="60%">
    <tr>
        <th>学生 编号</th>
        <th>学生姓名</th>
        <th>学生年龄</th>
        <th>学生成绩</th>
        <th>vs.index</th>
        <th>更新操作</th>
        <th>删除操作</th>
    </tr>
    <c:forEach items="${pageBean.list}" var="stu" varStatus="vs">
        <tr>
            <td>${stu.id }</td>
            <td>${stu.name }</td>
            <td>${stu.age }</td>
            <td>${stu.score }</td>
            <td>${vs.index }</td>
            <td><a href="/stumanager/servlet/StudentServlet?operate=preupdate&sid=${stu.id}">更新</a></td>
            <td><a href="/stumanager/servlet/StudentServlet?operate=delete&sid=${stu.id}">删除</a></td>
        </tr>
    </c:forEach>

    <tr>
        <td colspan="15" align="center">

            <a href="javascript:change(1,${pageBean.size})">首页</a>
            <c:if test="${pageBean.index==1}">
                上一页
            </c:if>
            <c:if test="${pageBean.index!=1}">
                <a href="javascript:change(${pageBean.index-1},${pageBean.size})">上一页</a>
            </c:if>
            <c:forEach items="${pageBean.numbers}" var="i">
                    <c:if test="${pageBean.index==i}">
                        [${i}]
                    </c:if>
                    <c:if test="${pageBean.index!=i}">
                        <a href="javascript:change(${i},${pageBean.size})">${i}</a>
                </c:if>
            </c:forEach>

            <c:if test="${pageBean.index==pageBean.totalPageCount}">
                下一页
            </c:if>
            <c:if test="${pageBean.index!=pageBean.totalPageCount}">
                <a href="javascript:change(${pageBean.index+1},${pageBean.size})">下一页</a>
            </c:if>
            <a href="javascript:change(${pageBean.totalPageCount},${pageBean.size})">末页</a>
            每页
            <select  onchange="changeSize(this.value,${pageBean.index})">
                <c:forEach begin="5" end="25" step="5" var="size">
                    <c:if test="${pageBean.size==size}">
                        <option value="${size}" selected>${size}</option>
                    </c:if>
                    <c:if test="${pageBean.size!=size}">
                        <option value="${size}">${size}</option>
                    </c:if>
                </c:forEach>
            </select>条记录
            直接跳到第<input type="text" size="2" id="indexn" value="${pageBean.index}">
            页 <input type="button" value="Go" onclick="changeIndex(${pageBean.size})"> 共 ${pageBean.totalCount}条记录

        </td>

    </tr>
</table>



</body>
</html>
