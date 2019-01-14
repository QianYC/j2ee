<%--
  Created by IntelliJ IDEA.
  User: 17678
  Date: 2018/12/31
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <title>Login error</title>
</head>
<body>
<h2>对不起，用户名密码不正确！</h2>
<form action=<%= request.getContextPath()+"index.jsp" %> method="get">
    <input type="submit" value="返回">
</form>
</body>
</html>
