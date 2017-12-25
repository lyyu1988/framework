<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">

<link href="<%=path%>/resource-page/js/layui/css/layui.css" rel="stylesheet">

<script src="<%=path%>/resource-page/js/jquery-3.1.0.js"></script>
<script src="<%=path%>/resource-page/js/jquery-form.js"></script>
<script src="<%=path%>/resource-page/js/layui/layui.js"></script>
<script src="<%=path%>/resource-page/js/utils/utils.js"></script>

<script type="text/javascript">
    var constants={};
    $(function(){
        var h=document.body.clientHeight,
            w=document.body.clientWidth;
        console.log("h="+h);
        constants.tableHeight=h-194;
    });
</script>

<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
<script src="<%=path%>/resource-page/js/html5.min.js"></script>
<script src="<%=path%>/resource-page/js/respond.min.js"></script>
<![endif]-->