<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>详情</title>
</head>
<body>
<hr>
<div>
    <h5>服装详情</h5>
    <table border="1" width="700px">
        <tr>
            <th>序号</th>
            <th>服装</th>
            <th>品牌</th>
            <th>性别</th>
            <th>颜色</th>
            <th>价格</th>
            <th>尺数</th>
        </tr>

        <tr>
            <td>1</td>
            <td>${detail.cloth.cname}</td>
            <td>${detail.cloth.ebrand}</td>
            <td>${detail.cloth.cgender}</td>
            <td>${detail.dcolor}</td>
            <td>${detail.dprice}</td>
            <td>${detail.dsize}</td>
        </tr>
    </table>
</div>
</body>
</html>
