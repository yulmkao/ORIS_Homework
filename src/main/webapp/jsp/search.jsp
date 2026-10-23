<%--
  Created by IntelliJ IDEA.
  User: yulmkao
  Date: 23.10.2026
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
<h1>поиск водителей</h1>
<form action="/search" method="post">
    <input type="text" name="email" placeholder="введите email"><br><br>
    <input type="text" name="city" placeholder="введите город"><br><br>
    <input type="text" name="phone" placeholder="введите телефон"><br><br>
    <input type="submit" value="найти">
</form>
</body>
</html>