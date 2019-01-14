<%@ page import="model.PurchaseResult" %><%--
  Created by IntelliJ IDEA.
  User: 17678
  Date: 2019/1/5
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<jsp:useBean id="purchaseResult" type="model.PurchaseResult" scope="request"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% int status = purchaseResult.getStatus(); %>
<table border="1">
    <tr>
        <td>购买结果</td>
        <td><%= status== PurchaseResult.FAIL?"失败"
                :status== PurchaseResult.SUCCESS?"成功":"享受折扣" %></td>
    </tr>
    <% if (status == PurchaseResult.FAIL) { %>
    <tr>
        <td>购买失败！</td>
    </tr>
    <% }else{ %>

    <tr>
        <td>总价</td>
        <td><%= purchaseResult.getTotal() %>
        </td>
    </tr>
    <tr>
        <td>账户余额</td>
        <td><%= purchaseResult.getBalance() %>
        </td>
    </tr>
    <% } %>
</table>
<form action=<%= response.encodeURL(request.getContextPath()+"/shop?page=1") %> method="get">
    <input type="submit" value="返回">
</form>
</body>
</html>
