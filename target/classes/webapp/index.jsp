<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<% session.setMaxInactiveInterval(60); %>
<html>
<body>
    <form action=<%= response.encodeURL(request.getContextPath()+"/login") %> method="post">
        userName:<br>
        <input type="text" name="userName"><br>
        password:<br>
        <input type="password" name="password"><br>
        <input type="submit" value="登录">
    </form>
    <% Integer login = (Integer) application.getAttribute("login"); %>
    <% Integer non_login = (Integer) application.getAttribute("non_login"); %>
    <% int total = login + non_login; %>
    <table>
        <tr><td>在线人数 : <%= total %></td></tr>
        <tr><td>登陆人数 : <%= login %></td></tr>
        <tr><td>游客人数 : <%= non_login %></td></tr>
    </table>
</body>
</html>
