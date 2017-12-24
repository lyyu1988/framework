<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="<%=path%>/login" method="post">
        用户名：<input type="text" name="username"/>
        密&nbsp码：<input type="password" name="password"/>
        ${errorMsg}
        <input type="submit" value="submit"/>
    </form>
</body>
</html>
