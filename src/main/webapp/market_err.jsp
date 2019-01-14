<%--
  Created by IntelliJ IDEA.
  User: 17678
  Date: 2019/1/4
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <title>Market error</title>
</head>
<body>
    购买数量请输入正整数！<br>
    <form action=<%= response.encodeURL(request.getContextPath()+"/shop?page=1") %> method="get">
        <input type="submit" value="返回">
    </form>
</body>
</html>
