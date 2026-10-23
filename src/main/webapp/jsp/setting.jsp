<%--
  Created by IntelliJ IDEA.
  User: tron5
  Date: 10.10.2026
  Time: 8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Settings</title>
</head>
<body>
<h1 style="color: ${cookie.get("color_cookie").value}">hello!</h1>
<div>
    <form action="/settings" method="post">
        <select size="3" name="color">
            <option value="red">red</option>
            <option value="green">green</option>
            <option value="black">black</option>
            <option value="blue">blue</option>
        </select>
        <input type="submit" value="save color">
    </form>
</div>
</body>
</html>