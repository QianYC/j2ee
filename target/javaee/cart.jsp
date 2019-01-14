<%@ page import="java.util.Map" %>
<%@ page import="model.CartItem" %><%--
  Created by IntelliJ IDEA.
  User: 17678
  Date: 2019/1/5
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<jsp:useBean id="cart" type="model.Cart" scope="session"/>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<table border="1">
    <tr>
        <td>商品名称</td>
        <td>价格</td>
        <td>数量</td>
    </tr>
    <%
        for (CartItem item : cart.getItems()) {
            if (item.getNum() > 0) {
    %>
                <tr>
                    <td><%= item.getName() %></td>
                    <td><%= item.getPrice() %></td>
                    <td><%= item.getNum() %></td>
                </tr>
    <%
            }
        }
    %>
    <tr>
        <td>总计</td>
        <td><%= cart.getTotal() %></td>
    </tr>
</table>
<form action=<%= response.encodeURL(request.getContextPath()+"/cart") %> method="post">
    <input type="submit" value="购买">
</form>
<form action=<%= response.encodeURL(request.getContextPath()+"/shop") %> method="get">
    <input type="submit" value="返回">
</form>
</body>
</html>
