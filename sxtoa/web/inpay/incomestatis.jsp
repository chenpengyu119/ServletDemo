<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><head>
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
    <script type="text/javascript" src="/sxtoa/js/jquery.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
    <script>
        $(function () {

            $.ajax({
                url:"/sxtoa/servlet/ExpenseServlet?method=getInData",
                success:function (data) {
                    eval("var jsonObj="+data)

                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('main'));

                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '公司收入统计柱状图'
                        },
                        tooltip: {},
                        legend: {
                            data:['收入']
                        },
                        xAxis: {
                            data: jsonObj[0]
                        },
                        yAxis: {},
                        series: [{
                            name: '收入',
                            type: 'bar',
                            data: jsonObj[1]
                        }]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);

                }
            });

        })
    </script>

</head>
<body>
<div id="main" style="width: 600px;height:400px;"></div>


</body>
</html>
