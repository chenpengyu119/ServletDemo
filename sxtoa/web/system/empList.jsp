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
    <link href="css/select.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="js/jquery.js"></script>

    <script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="js/select-ui.min.js"></script>
    <script type="text/javascript" src="editor/kindeditor.js"></script>
    <script type="text/javascript" src="js/jq-paginator.js"></script>
    <script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(document).ready(function(e) {
            $(".select1").uedSelect({
                width : 100
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



        function getData(index){

            // 用户名
            var empId = $("#empId").val();
            // 部门
            var option = $("#select1 option:selected");
            var deptNo = option.val();
            // 是否在职
            var inp = $("#onduty input:checked");
            var onduty = inp.val();
            // 入职时间
            var hiredate = $("#hiredate").val();
            console.info(empId+"\t"+deptNo+"\t"+onduty+"\t"+hiredate);
            var ind;
            if (index == null){
                ind = $("#index").html()
            } else {
                ind = index;
            }

            $.ajax({
                type:"POST",
                url:"/sxtoa/servlet/EmployeeServlet?method=list",
                dataType:"text",
                data:{"index":ind,
                    "size":"5",
                    "empId":empId,
                    "deptNo":deptNo,
                    "onduty":onduty,
                    "hiredate":hiredate
                },
                success:function (data) {
                    console.info(data);
                    var arr = data.split("|||");
                    eval("var page="+arr[0]);
                    eval("var deptList="+arr[1]);
                    var str = "";
                    for (var i=0;page.list.length>i;i++){
                        str += "<tr>\n" +
                            "<td>\n" +
                            " <input name='' type='checkbox' value='' />\n" +
                            " </td>\n" +
                            "<td>"+page.list[i].empId+"</td>\n" +
                            " <td>"+page.list[i].realName+'</td>\n' +
                            "  <td>"+page.list[i].dept.deptName+"</td>\n" +
                            "            <td>"+page.list[i].pos.pname+'</td>\n'+
                            '           <td>'+page.list[i].hiredate+'</td>\n' +
                            '            <td>'+page.list[i].phone+'</td>\n' +
                            "            <td>\n" +
                            "                <a href='empInfo.html' class='tablelink'>查看</a>\n" +
                            "                <a href='/sxtoa/servlet/EmployeeServlet?method=findById&empId=" +
                            page.list[i].empId +"'"+
                            "class='tablelink'>修改</a>\n" +
                            "                <a href='#' class='tablelink click'> 删除</a>\n" +
                            "                <a href='#' class='tablelink'> 重置密码</a>\n" +
                            "            </td>\n" +
                            "        </tr>";
                    }
                    var tb = $("#empTb");
                    tb.html(str);
                    // 总页数
                    $("#total").html(page.totalCount);
                    // 设置当前页码
                    ind =  $("#index").html(page.index);
                    ind = parseInt(ind);
                    // 隐藏域记录最大页码
                    $("#maxpagehidden").val(page.totalPageCount);


                    // 设置页码
                    var pageno = $("#pageno");
                    var str = "";
                    str+="<li class='paginItem'><a href='javascript:pre();'><span class='pagepre'></span></a></li>";

                    for(var i=0;i<page.numbers.length;i++){
                        console.info("ind:"+ind);
                        if (ind == page.index){
                            str+=" <li class='paginItem'><a id='pre' href='javascript:getData("+page.numbers[i]+");'>"+page.numbers[i]+"</a></li>"
                        }else {
                            str+="<li class='paginItem'><a href='javascript:getData("+page.numbers[i]+");'>"+page.numbers[i]+"</a></li>"
                        }
                    }
                    str+=" <li class='paginItem'><a id='pagenxt' href='javascript:next();'><span class='pagenxt'></span></a></li>";
                    pageno.html(str);


                    var deptListStr = "";
                    console.info("-----------------"+arr[2]);
                    deptListStr+= '<option value='+'0'+'>'+'不限'+"</option>";
                    // 设置部门
                    for(var i=0;i<deptList.length;i++){
                        deptListStr += '<option value='+deptList[i].deptNo+'>'+deptList[i].deptName+"</option>";
                    }
                    $("#select1").html(deptListStr);
                }
            })
        }
        // 下一页
        function next() {
            // 下一页
            var index = $("#index").html();
            var maxpage = $("#maxpagehidden").val();
            if (index == maxpage ){
                $("#pagenxt").attr("disabled","disabled");
                return;
            }
            index = parseInt(index);
            getData(index+1);
        }

        // 上一页
        function pre(){
            var index = $("#index").html();
            index = parseInt(index);
            if (index==1){
                $("#pre").attr("disabled","disabled");
            } else{
                getData(index-1);
            }
        }

        $(function () {
            getData(1);
        })
        function clearData() {
            // 用户名
            var empId = $("#empId");
            empId.val("");
            // 部门
            var option = $("#select1 option:eq(0)");

            option.prop("selected", "false");

            var deptNo = option.val();
            // 是否在职
            var inp = $("#onduty input:eq(0)");
            inp.prop("checked","false");
            var onduty = inp.val();
            // 入职时间
            $("#hiredate").val("");
            console.info(empId+"\t"+deptNo+"\t"+onduty+"\t"+hiredate);
        }

        
    </script>

</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">人事管理</a></li>
        <li><a href="#">员工管理</a></li>
    </ul>
</div>

<div class="rightinfo">

    <ul class="prosearch">
        <li>
            <label>查询：</label><i>用户名</i>
            <a>
                <input name="" id="empId" type="text" class="scinput" />
            </a><i>所属部门</i>
            <a>
                <select class="select1" id="select1">
                </select>
            </a>

        </li>
        <li id="onduty">
            <label>是否在职：</label>
            <input id="onduty1" name="onduty" type="radio" value="1" checked="checked" />&nbsp;是&nbsp;&nbsp;
            <input id="onduty2" name="onduty" type="radio" value="0" />&nbsp;否
        </li>
        <li>
            <label>入职时间：</label>
            <a>
                <input name="hiredate" id="hiredate" type="text" class="scinput" onfocus="WdatePicker()" />
            </a>
        </li>
        <a>
            <input name="" type="button" class="sure" value="查询" onclick="getData()" />
        </a>
        <a>
            <input name=""  type="button" class="sure" value="清空" onclick="clearData()" />
        </a>
    </ul>

    <div class="formtitle1"><span>员工列表</span></div>

    <table class="tablelist">
        <thead>
        <tr>
            <th>
                <input name="" type="checkbox" value="" checked="checked" />
            </th>
            <th>用户名<i class="sort"><img src="images/px.gif" /></i></th>
            <th>真实姓名</th>
            <th>所属部门</th>
            <th>所属岗位</th>
            <th>入职时间</th>
            <th>联系方式</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="empTb">

        </tbody>
    </table>

    <div class="pagin">
        <input type="hidden" id="maxpagehidden"/>
        <div class="message">共<i class="blue" id="total"></i>条记录，当前显示第&nbsp;<i class="blue" id="index"></i>页</div>
        <ul class="paginList" id="pageno">
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