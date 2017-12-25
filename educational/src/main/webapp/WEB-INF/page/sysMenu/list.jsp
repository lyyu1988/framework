<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="layui-row layui-col-space10">
    <div class="layui-col-md2 layui-col-md-offset10">
        <button class="layui-btn">新增菜单</button>
    </div>
</div>
<div class="layui-row">
    <table class="layui-hide" lay-data="{id: 'table-${tabMenuId}'}"></table>
</div>
<script>
    layui.use(["table","element","layer"], function(){
        var $button=$("#${tabMenuId}").find("button"),element = layui.element,
            layer = layui.layer,table = layui.table;

        $button.click(function () {
            //utils.tabAdd(element,"新增","sysMenu/add");
            //utils.layerAdd(layer,"新增","sysMenu/add","addMenu");
            //Ajax取
            $.get("<%=path%>/sysMenu/addOrUpdate?tabMenuId=addMenu", {}, function(str){
                layer.open({
                    type: 1,
                    title:"新增菜单",
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area:["1200px","600px"],
                    shadeClose:true,
                    id:"addMenu",
                    maxmin:true,
                    btn:["保存","取消"],
                    btnAlign:"c",
                    success:function (layero, index) {
                        layero.find(".layui-btn").data("tableId","table-${tabMenuId}");
                    },
                    yes:function (index,layero) {
                        layero.find(".layui-btn").click();
                        // table.reload("table-${tabMenuId}",{url:'<%=path%>/sysMenu/data'});
                    }
                });
            });
        });

        table.render({
            //elem: "#${tabMenuId}>table",
            elem: "#${tabMenuId} table",
            url:'<%=path%>/sysMenu/data',
            height: constants.tableHeight,
            id:"table-${tabMenuId}",
            response: {
                statusName: 'code', //数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                msgName: 'hint', //状态信息的字段名称，默认：msg
                countName: 'total', //数据总数的字段名称，默认：count
                dataName: 'records' //数据列表的字段名称，默认：data
            },
            cols: [[
                {type:'checkbox',fixed: 'left'},
                {field:'name', title: '菜单名'},
                {field:'parentName', title: '父节点'},
                {field:'menuOrder', title: '排序', sort: true}
            ]],
            page: true
        });

    });
</script>