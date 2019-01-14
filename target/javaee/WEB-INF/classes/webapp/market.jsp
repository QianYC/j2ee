<%@ page import="java.util.List" %>
<%@ page import="entity.Commodity" %><%--
  Created by IntelliJ IDEA.
  User: 17678
  Date: 2018/12/31
  Time: 21:11 
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <title>Market</title>
</head>
<body>
<jsp:useBean id="list" type="model.ProductList" scope="request"/>
<%
    int ppage = ((Integer) request.getAttribute("page"));
    int maxPage = ((Integer) request.getAttribute("maxPage"));
%>
    <form action=<%= response.encodeURL(request.getContextPath()+"/shop?page="+ppage) %> method="post">
<%
    List<Commodity> commodities = list.getList();
    for (Commodity commodity : commodities) {
%>

        <%= commodity.getName()+" 价格: "+commodity.getPrice()+" 元" %>
        <input type="text" name=<%= commodity.getName()%> ><br>

<%
    }
%>
    <input type="submit" value="加入购物车">
    </form>
    <form action=<%= response.encodeURL(request.getContextPath()+"/cart") %> method="get">
        <input type="submit" value="购物车">
    </form>
<%
    if (ppage!=1){
%>
    <a href=<%= response.encodeURL(request.getContextPath()+"/shop?page="+(ppage-1)) %>><</a>&emsp;
<%
    }
    for (int i = 1; i <= maxPage; i++) {
%>
    <a href=<%= response.encodeURL(request.getContextPath()+"/shop?page="+i) %>><%= i %></a>&emsp;
<%
    }
    if (ppage!=maxPage){
%>
    <a href=<%= response.encodeURL(request.getContextPath()+"/shop?page="+(ppage+1)) %>>></a>
<%
    }
%>
</body>
</html>
