<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link href="css/select.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="js/jquery.js"></script>

    <script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="js/select-ui.min.js"></script>
    <script type="text/javascript" src="editor/kindeditor.js"></script>
    <script type="text/javascript">
        $(document).ready(function(e) {
            $(".select1").uedSelect({
                width : 200
            });

        });
    </script>
    <script type="text/javascript">
        $(document).ready(function(){
            $(".click").click(function(){
                $(".tip").fadeIn(200);
            });

            $(".tiptop a").click(function(){
                $(".tip").fadeOut(200);
            });

            $(".sure").click(function(){
                $(".tip").fadeOut(100);
            });

            $(".cancel").click(function(){
                $(".tip").fadeOut(100);
            });

        });
        $(function () {

            // 导出按钮
            $("#exportXls").bind("click",function () {
                console.info("点了导出")
                // 获取数据
                var empId = $("#empId").val();
                var deptNo = $("#deptNo option:selected").val();
                var dtDate = $("#dtDate").val();
                location.href = "/sxtoa/servlet/DutyServlet?method=exportXls&empId="+empId+"&deptNo="+deptNo+"&dtDate="+dtDate;
            })

            // 获取部门数据
            $.ajax({
                url:"/sxtoa/servlet/EmployeeServlet?method=getDept",
                success:function (data) {

                    eval("var jsonObj ="+data);
                    var deptListStr = "";
                    console.info("-----------------"+data);
                    deptListStr+= '<option value='+'0'+'>'+'不限'+"</option>";
                    // 设置部门
                    for(var i=0;i<jsonObj.length;i++){
                        deptListStr += '<option value='+jsonObj[i].deptNo+'>'+jsonObj[i].deptName+"</option>";
                    }
                    $("#deptNo").html(deptListStr);
                    getList();
                }
            });
            $("#querybt").bind("click",function () {
                getList();
            })

        });
        function getList() {
            // 获取考勤数据
            var empId = $("#empId").val();
            var deptNo = $("#deptNo option:selected").val();
            var dtDate = $("#dtDate").val();
            $.ajax({
                url:"/sxtoa/servlet/DutyServlet?method=list",
                data:{
                    "empId":empId,
                    "deptNo":deptNo,
                    "dtDate":dtDate
                },
                success:function (data) {
                    eval("var jsonObj="+data);
                    console.info(data);
                    var str = "";
                    for (var i=0;i<jsonObj.length;i++){
                        str+="<tr>\n" +
                            "            <td>\n" +
                            "                <input name=\"\" type=\"checkbox\" value=\"\" />\n" +
                            "            </td>\n" +
                            "            <td>"+jsonObj[i].empId+"</td>\n" +
                            "            <td>"+jsonObj[i].emp.realName+"</td>\n" +
                            "            <td>"+jsonObj[i].emp.dept.deptName+"</td>\n" +
                            "            <td>"+jsonObj[i].dtDate+"</td>\n" +
                            "            <td>"+jsonObj[i].signinTime+"</td>\n" +
                            "            <td>"+jsonObj[i].signoutTime+"</td>\n" +
                            "        </tr>";
                    }

                    $("#tbList").html(str);
                }
            });
        }


    </script>

</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">考勤管理</a></li>
        <li><a href="#">考勤管理</a></li>
    </ul>
</div>

<div class="rightinfo">

    <ul class="prosearch">
        <li>
            <label>查询：</label><i>用户名</i>
            <a>
                <input name="" id ="empId" type="text" class="scinput" />
            </a><i>所属部门</i>
            <a>
                <select class="select1" id="deptNo">
                    <option>总裁办</option>
                </select>
            </a>
            <i>考勤时间</i>
            <a>
                <input name="" id="dtDate" type="text" class="scinput" />
            </a>
            <a>
                <input id ="querybt" name="" type="button" class="sure" value="查询" />

            </a>
            <a>
                <input name="" id="exportXls" type="button" class="scbtn2" value="导出"/>

            </a>

        </li>


    </ul>

    <div class="formtitle1"><span>考勤管理</span></div>

    <table class="tablelist">
        <thead>
        <tr>
            <th>
                <input name="" type="checkbox" value="" checked="checked" />
            </th>
            <th>用户名<i class="sort"><img src="images/px.gif" /></i></th>
            <th>真实姓名</th>
            <th>所属部门</th>
            <th>出勤日期</th>
            <th>签到时间</th>
            <th>签退时间</th>
        </tr>
        </thead>
        <tbody id="tbList">


        </tbody>
    </table>

    <div class="pagin">
        <div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
        <ul class="paginList">
            <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
            <li class="paginItem"><a href="javascript:;">1</a></li>
            <li class="paginItem current"><a href="javascript:;">2</a></li>
            <li class="paginItem"><a href="javascript:;">3</a></li>
            <li class="paginItem"><a href="javascript:;">4</a></li>
            <li class="paginItem"><a href="javascript:;">5</a></li>
            <li class="paginItem more"><a href="javascript:;">...</a></li>
            <li class="paginItem"><a href="javascript:;">10</a></li>
            <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul>
    </div>

    <div class="tip">
        <div class="tiptop"><span>提示信息</span>
            <a></a>
        </div>

        <div class="tipinfo">
            <span><img src="images/ticon.png" /></span>
            <div class="tipright">
                <p>是否确认对信息的修改 ？</p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
            </div>
        </div>

        <div class="tipbtn">
            <input name="" type="button" class="sure" value="确定" />&nbsp;
            <input name="" type="button" class="cancel" value="取消" />
        </div>

    </div>

</div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

</body>

</html>