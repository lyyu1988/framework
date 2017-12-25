<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<table class="layui-hide"></table>
<script>
    layui.use('table', function(){
        var $table=$("#${tabMenuId}").find("table");
        console.log("menuId=${tabMenuId}");

        var table = layui.table;

        table.render({
            //elem: "#${tabMenuId}>table",
            elem: $table[0],
            url:'<%=path%>/sysResource/data',
            cols: [[
                {field:'id', width:80, title: 'ID', sort: true},
                {field:'username', width:80, title: '用户名'},
                {field:'sex', width:80, title: '性别', sort: true},
                {field:'city', width:80, title: '城市'},
                {field:'sign', title: '签名', minWidth: 150},
                {field:'experience', width:80, title: '积分', sort: true},
                {field:'score', width:80, title: '评分', sort: true},
                {field:'classify', width:80, title: '职业'},
                {field:'wealth', width:135, title: '财富', sort: true}
            ]],
            page: true
        });
    });
</script>