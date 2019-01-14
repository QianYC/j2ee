<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<% session.setMaxInactiveInterval(60); %>
<jsp:useBean id="loginResult" type="model.LoginResult" scope="request"/>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h2>Hello, <jsp:getProperty name="loginResult" property="userName"/> !</h2><br>
    Your balance is <jsp:getProperty name="loginResult" property="money"/>
<form action=<%= response.encodeURL(request.getContextPath()+"/shop?page=1") %> method="get">
       <input type="submit" value="开始购物">
</form>
</body>
</html>