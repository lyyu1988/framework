<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<form class="layui-form" style="margin-top: 10px;" action="">
    <input type="hidden" value="" name="id">
    <div class="layui-container layui-col-space10">
        <div class="layui-row">
            <div class="layui-col-md6">
                <label class="layui-form-label">父节点</label>
                <div class="layui-input-block">
                    <select name="parentId">
                        <option value="0">父节点</option>
                    </select>
                </div>
            </div>
            <div class="layui-col-md6">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="title" autocomplete="off" placeholder="请输入标题"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-row">
            <div class="layui-col-md6">
                <label class="layui-form-label">链接</label>
                <div class="layui-input-block">
                    <input type="text" name="path" lay-verify="title" autocomplete="off" placeholder="请输入链接"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-col-md6">
                <label class="layui-form-label">排序</label>
                <div class="layui-input-block">
                    <input type="text" name="menuOrder" lay-verify="number" autocomplete="off" placeholder="请输入排序"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item" style="display: none;">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addMenu">立即提交</button>
            </div>
        </div>
    </div>
</form>
<script>
    layui.use(["form", "layedit", "laydate","table"], function () {
        var form = layui.form, layer = layui.layer, $self = $("#${tabMenuId}"), $select = $self.find("select"),
            list = JSON.parse('${sysMenuList}'),table = layui.table;

        $(list).each(function (i) {
            $select.append("<option value='" + list[i].id + "'>" + list[i].name + "</option>");
        });

        form.render("select");
        //自定义验证规则
        form.verify({
            title: function (value) {
                if (value.length < 3) {
                    return "标题至少得3个字符啊";
                }
            },
            number:function (value) {
                if(!/^\d*$/.test(value)){
                    return "只能是数字";
                }
            }
        });

        //监听提交
        form.on("submit(addMenu)", function (data) {
            $.ajax({
                url: "<%=path%>/sysMenu/addOrUpdate",
                type: "post",
                dataType: "json",
                data: data.field,
                success: function (data) {
                    if (data.code == "0") {
                        layer.alert("保存成功");
                        layer.closeAll("page");
                        console.log($self.find(".layui-btn").data("tableId"));
                        table.reload($self.find(".layui-btn").data("tableId"),{url:'<%=path%>/sysMenu/data'});
                    } else {
                        layer.alert(data.msg);
                    }
                }
            });
            return false;
        });
    });
</script>