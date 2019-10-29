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
    <script type="text/javascript">
        $(document).ready(function(e) {
            $(".select1").uedSelect({
                width : 345
            });

        });
    </script>
    <script type="text/javascript">
        function addPhoto(){
            //获取上传图片li
            var li = $("#uploadphoto");
            //准备添加的dom对象
            var content=$("<span><label>&nbsp;</label><input name='photo' type='file' class='dfinput' /><i></i>"+
                "<input type='button'  class='btn'  value='删除' onclick='removeCurrSpan(this)'/></span>");
            //加入到指定位置
            li.append(content);
        }

        function removeCurrSpan(obj){
            $(obj).parent().remove();

        }

        function addExpenseItem(id){
            //获取上级标签
            var table = $(id);

            //创建当前标签
            var content = $('<tr>'+
                '<td><div class="vocation"><select class="select1" name="type">'+
                '<option value="t1">通信费用</option><option value="t2">办公室耗材</option><option value="t3">住宿费用</option><option value="t4">房租水电</option><option value="t5">其他</option>'+
                '</select></div></td>'+
                '<td><input name="amount" type="text" class="dfinput" /><i></i></td>'+
                '<td><input name="itemDesc" type="text" class="dfinput" /><i></i></td>'+
                '<td><input type="button"  class="btn"  value="删除" onclick="removeCurrItem(this)"/></td></tr>');

            //加入上级标签
            table.append(content);
            $(".select1").uedSelect({
                width : 345
            });
        }

        function removeCurrItem(obj){
            $(obj).parent().parent().remove();

        }
    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">报销管理</a></li>
        <li><a href="#">添加报销</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form action="servlet/ExpenseServlet?method=add" method="post">
        <ul class="forminfo">
            <li>
                <label>报销人</label>
                <input name="empId" type="text" class="dfinput"  value="${emp.empId}"/><i></i></li>
            <li>
            <li>
                <label>具体报销项</label>
                <input type="button"  class="btn"  value="添加报销项" onclick="addExpenseItem('#table1')"/>

                <table border="" cellspacing="" cellpadding="" width="70%" id="table1">
                    <tr>
                        <td>报销类型</td>
                        <td>报销费用</td>
                        <td>费用说明</td>
                    </tr>
                    <tr>
                        <td>
                            <div class="vocation">
                                <select class="select1" name="type">
                                    <option value="t1">通信费用</option>
                                    <option value="t2">办公室耗材</option>
                                    <option value="t3">住宿费用</option>
                                    <option value="t4">房租水电</option>
                                    <option value="t5">其他</option>
                                </select>
                            </div>
                        </td>
                        <td><input name="amount" type="text" class="dfinput" /><i></i></td>
                        <td><input name="itemDesc" type="text" class="dfinput" /><i></i></td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </li>
            <li>

            </li>
            <li id="uploadphoto">
                <label>上传报销图片</label>
                <span>
                    <input name="photo" type="file" class="dfinput" /><i></i>
                    <input type="button"  class="btn"  value="添加图片" onclick="addPhoto()"/>
                </span>
            </li>
            <li>
                <label>审批人<b>*</b></label>
                <div class="vocation">
                    <select class="select1" name="nextAuditor">
                        <option value="${emp.mgrId}">${emp.mgrId}</option>
                    </select>
                </div>
                &nbsp;&nbsp;
                <input name="" type="text" class="dfinput" placeholder="也可以在此输入首字母帮助显示" />
            </li>
            </li>
            <li>
                <label>总备注信息</label>
                <textarea name="expDesc" cols="" rows="" class="textinput"></textarea>
            </li>
            <li>
                <label>&nbsp;</label>
                <input name="" type="submit" class="btn" value="确认保存" />
            </li>
        </ul>
    </form>

    ${error}
</div>

</body>

</html>